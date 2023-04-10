package org.armzbot.services;

import org.armzbot.models.Reservation;
import org.armzbot.repository.ReservationRepository;
import org.armzbot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationServices {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    public ReservationServices(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public List<Reservation> getAllReservationsByUserId(String user_id) throws IOException {
        if (user_id == null) {
            throw new IOException("user_id is null");
        }
        if(!userRepository.existsById(user_id)) {
            throw new IOException("Not found user_id : " + user_id);
        }
        return reservationRepository.findByUserId(user_id);
    }

    public Reservation getReservationById(String reserve_id) throws IOException {
        Optional<Reservation> repository = reservationRepository.findById(reserve_id);
        if (!repository.isPresent()) {
            throw new IOException("Not found reservation with id : " + reserve_id);
        }
        return repository.get();
    }

    public void addReservation(String user_id, Reservation reservation) throws IOException {
        Reservation tmp = userRepository.findById(user_id).map(user->{
            reservation.setUser_id(user);
            return reservationRepository.save(reservation);}
        ).orElseThrow(()->new IOException("Not found Tutorial with id =  "+ user_id));
    }

    public void updateReservation(String reserve_id, Reservation reservation) throws IOException{
        Optional<Reservation> repository = reservationRepository.findById(reserve_id);
        if(repository.isEmpty()) throw new IOException("Not found reservation id : " + reserve_id);
        Reservation result = repository.get();

        if(reservation.getUser_id()!=null)
            result.setUser_id(reservation.getUser_id());

        if(reservation.getAcc_id()!=null)
            result.setAcc_id(reservation.getAcc_id());

        result.setGuest_amt(reservation.getGuest_amt());

        if(reservation.getCheckIn()!=null)
            result.setCheckIn(reservation.getCheckIn());

        if(reservation.getCheckOut()!=null)
            result.setCheckOut(reservation.getCheckOut());

        if(reservation.getPayment_status()!=null)
            result.setPayment_status(reservation.getPayment_status());

        reservationRepository.save(result);
    }

    public void deleteReservationById(String reserve_id) throws IOException {
        Optional<Reservation> repository = reservationRepository.findById(reserve_id);
        if (!repository.isPresent()) {
            throw new IOException("Not found id : " + reserve_id);
        }
        Reservation deleteReserve = repository.get();
        deleteReserve.setIs_active(false);
        reservationRepository.save(deleteReserve);
    }

    public void deleteAllReservationsOfUser(String user_id) {
        reservationRepository.deleteByUserId(user_id);
        List<Reservation> repositories = reservationRepository.findByUserId(user_id);
        for (int i = 0; i < repositories.size(); i++) {
            Reservation deleteReserve = repositories.get(i);
            deleteReserve.setIs_active(false);
            reservationRepository.save(deleteReserve);
        }
    }
}
