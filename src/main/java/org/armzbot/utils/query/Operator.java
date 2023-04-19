package org.armzbot.utils.query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;


@Log4j2
public enum Operator {
    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());
            Expression<?> key = root.get(request.getKey());
            return cb.and(cb.equal(key, value), predicate);
        }
    },

    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());
            Expression<?> key = root.get(request.getKey());
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },

    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Expression<String> key = root.get(request.getKey());
            return cb.and(cb.like(cb.upper(key), "%" + request.getFrom().toUpperCase() + "%"), predicate);
        }
    },

    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> inClause = cb.in(root.get(request.getKey()));
            for (Object value : values) {
                inClause.value(request.getFieldType().parse(value.toString()));
            }
            return cb.and(inClause, predicate);
        }
    }, MORE_THAN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());


            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number number = (Number) value;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.gt(key, number), predicate);
            }

            return predicate;
        }
    }, MORE_THAN_OR_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());


            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number number = (Number) value;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.ge(key, number), predicate);
            }

            return predicate;
        }
    },

    LESS_THAN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number number = (Number) value;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.lt(key, number), predicate);
            }

            return predicate;
        }
    },

    BEFORE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime date = (LocalDateTime) value;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.lessThan(key, date), predicate);
            }

            return predicate;
        }
    },

    BEFORE_OR_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime date = (LocalDateTime) value;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.lessThanOrEqualTo(key, date), predicate);
            }

            return predicate;
        }
    },

    AFTER {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime date = (LocalDateTime) value;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.greaterThan(key, date), predicate);
            }

            return predicate;
        }
    },

    AFTER_OR_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime date = (LocalDateTime) value;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.greaterThanOrEqualTo(key, date), predicate);
            }

            return predicate;
        }
    },

    LESS_THAN_OR_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());


            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number number = (Number) value;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.le(key, number), predicate);
            }

            return predicate;
        }
    },

    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = request.getFieldType().parse(request.getFrom());
            Object valueTo = request.getFieldType().parse(request.getTo());
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = root.get(request.getKey());
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = root.get(request.getKey());
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);

}
