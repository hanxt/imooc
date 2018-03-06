package net.aexit.galaxy.websky.common.model;

import java.util.ArrayList;
import java.util.List;

public class SysDictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public SysDictionaryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDicCodeIsNull() {
            addCriterion("dic_code is null");
            return (Criteria) this;
        }

        public Criteria andDicCodeIsNotNull() {
            addCriterion("dic_code is not null");
            return (Criteria) this;
        }

        public Criteria andDicCodeEqualTo(String value) {
            addCriterion("dic_code =", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotEqualTo(String value) {
            addCriterion("dic_code <>", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeGreaterThan(String value) {
            addCriterion("dic_code >", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dic_code >=", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLessThan(String value) {
            addCriterion("dic_code <", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLessThanOrEqualTo(String value) {
            addCriterion("dic_code <=", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLike(String value) {
            addCriterion("dic_code like", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotLike(String value) {
            addCriterion("dic_code not like", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeIn(List<String> values) {
            addCriterion("dic_code in", values, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotIn(List<String> values) {
            addCriterion("dic_code not in", values, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeBetween(String value1, String value2) {
            addCriterion("dic_code between", value1, value2, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotBetween(String value1, String value2) {
            addCriterion("dic_code not between", value1, value2, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicClassIsNull() {
            addCriterion("dic_class is null");
            return (Criteria) this;
        }

        public Criteria andDicClassIsNotNull() {
            addCriterion("dic_class is not null");
            return (Criteria) this;
        }

        public Criteria andDicClassEqualTo(String value) {
            addCriterion("dic_class =", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassNotEqualTo(String value) {
            addCriterion("dic_class <>", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassGreaterThan(String value) {
            addCriterion("dic_class >", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassGreaterThanOrEqualTo(String value) {
            addCriterion("dic_class >=", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassLessThan(String value) {
            addCriterion("dic_class <", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassLessThanOrEqualTo(String value) {
            addCriterion("dic_class <=", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassLike(String value) {
            addCriterion("dic_class like", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassNotLike(String value) {
            addCriterion("dic_class not like", value, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassIn(List<String> values) {
            addCriterion("dic_class in", values, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassNotIn(List<String> values) {
            addCriterion("dic_class not in", values, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassBetween(String value1, String value2) {
            addCriterion("dic_class between", value1, value2, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicClassNotBetween(String value1, String value2) {
            addCriterion("dic_class not between", value1, value2, "dicClass");
            return (Criteria) this;
        }

        public Criteria andDicPropertyIsNull() {
            addCriterion("dic_property is null");
            return (Criteria) this;
        }

        public Criteria andDicPropertyIsNotNull() {
            addCriterion("dic_property is not null");
            return (Criteria) this;
        }

        public Criteria andDicPropertyEqualTo(String value) {
            addCriterion("dic_property =", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyNotEqualTo(String value) {
            addCriterion("dic_property <>", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyGreaterThan(String value) {
            addCriterion("dic_property >", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("dic_property >=", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyLessThan(String value) {
            addCriterion("dic_property <", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyLessThanOrEqualTo(String value) {
            addCriterion("dic_property <=", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyLike(String value) {
            addCriterion("dic_property like", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyNotLike(String value) {
            addCriterion("dic_property not like", value, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyIn(List<String> values) {
            addCriterion("dic_property in", values, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyNotIn(List<String> values) {
            addCriterion("dic_property not in", values, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyBetween(String value1, String value2) {
            addCriterion("dic_property between", value1, value2, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicPropertyNotBetween(String value1, String value2) {
            addCriterion("dic_property not between", value1, value2, "dicProperty");
            return (Criteria) this;
        }

        public Criteria andDicValueIsNull() {
            addCriterion("dic_value is null");
            return (Criteria) this;
        }

        public Criteria andDicValueIsNotNull() {
            addCriterion("dic_value is not null");
            return (Criteria) this;
        }

        public Criteria andDicValueEqualTo(String value) {
            addCriterion("dic_value =", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotEqualTo(String value) {
            addCriterion("dic_value <>", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueGreaterThan(String value) {
            addCriterion("dic_value >", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueGreaterThanOrEqualTo(String value) {
            addCriterion("dic_value >=", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLessThan(String value) {
            addCriterion("dic_value <", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLessThanOrEqualTo(String value) {
            addCriterion("dic_value <=", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLike(String value) {
            addCriterion("dic_value like", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotLike(String value) {
            addCriterion("dic_value not like", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueIn(List<String> values) {
            addCriterion("dic_value in", values, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotIn(List<String> values) {
            addCriterion("dic_value not in", values, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueBetween(String value1, String value2) {
            addCriterion("dic_value between", value1, value2, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotBetween(String value1, String value2) {
            addCriterion("dic_value not between", value1, value2, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicNameIsNull() {
            addCriterion("dic_name is null");
            return (Criteria) this;
        }

        public Criteria andDicNameIsNotNull() {
            addCriterion("dic_name is not null");
            return (Criteria) this;
        }

        public Criteria andDicNameEqualTo(String value) {
            addCriterion("dic_name =", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotEqualTo(String value) {
            addCriterion("dic_name <>", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameGreaterThan(String value) {
            addCriterion("dic_name >", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameGreaterThanOrEqualTo(String value) {
            addCriterion("dic_name >=", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLessThan(String value) {
            addCriterion("dic_name <", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLessThanOrEqualTo(String value) {
            addCriterion("dic_name <=", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLike(String value) {
            addCriterion("dic_name like", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotLike(String value) {
            addCriterion("dic_name not like", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameIn(List<String> values) {
            addCriterion("dic_name in", values, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotIn(List<String> values) {
            addCriterion("dic_name not in", values, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameBetween(String value1, String value2) {
            addCriterion("dic_name between", value1, value2, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotBetween(String value1, String value2) {
            addCriterion("dic_name not between", value1, value2, "dicName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}