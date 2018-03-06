package net.aexit.galaxy.websky.common.model;

import java.util.ArrayList;
import java.util.List;

public class SysDbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public SysDbExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDbFlagIsNull() {
            addCriterion("db_flag is null");
            return (Criteria) this;
        }

        public Criteria andDbFlagIsNotNull() {
            addCriterion("db_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDbFlagEqualTo(String value) {
            addCriterion("db_flag =", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagNotEqualTo(String value) {
            addCriterion("db_flag <>", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagGreaterThan(String value) {
            addCriterion("db_flag >", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagGreaterThanOrEqualTo(String value) {
            addCriterion("db_flag >=", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagLessThan(String value) {
            addCriterion("db_flag <", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagLessThanOrEqualTo(String value) {
            addCriterion("db_flag <=", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagLike(String value) {
            addCriterion("db_flag like", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagNotLike(String value) {
            addCriterion("db_flag not like", value, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagIn(List<String> values) {
            addCriterion("db_flag in", values, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagNotIn(List<String> values) {
            addCriterion("db_flag not in", values, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagBetween(String value1, String value2) {
            addCriterion("db_flag between", value1, value2, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbFlagNotBetween(String value1, String value2) {
            addCriterion("db_flag not between", value1, value2, "dbFlag");
            return (Criteria) this;
        }

        public Criteria andDbNameIsNull() {
            addCriterion("db_name is null");
            return (Criteria) this;
        }

        public Criteria andDbNameIsNotNull() {
            addCriterion("db_name is not null");
            return (Criteria) this;
        }

        public Criteria andDbNameEqualTo(String value) {
            addCriterion("db_name =", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotEqualTo(String value) {
            addCriterion("db_name <>", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameGreaterThan(String value) {
            addCriterion("db_name >", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameGreaterThanOrEqualTo(String value) {
            addCriterion("db_name >=", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLessThan(String value) {
            addCriterion("db_name <", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLessThanOrEqualTo(String value) {
            addCriterion("db_name <=", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameLike(String value) {
            addCriterion("db_name like", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotLike(String value) {
            addCriterion("db_name not like", value, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameIn(List<String> values) {
            addCriterion("db_name in", values, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotIn(List<String> values) {
            addCriterion("db_name not in", values, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameBetween(String value1, String value2) {
            addCriterion("db_name between", value1, value2, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbNameNotBetween(String value1, String value2) {
            addCriterion("db_name not between", value1, value2, "dbName");
            return (Criteria) this;
        }

        public Criteria andDbTypeIsNull() {
            addCriterion("db_type is null");
            return (Criteria) this;
        }

        public Criteria andDbTypeIsNotNull() {
            addCriterion("db_type is not null");
            return (Criteria) this;
        }

        public Criteria andDbTypeEqualTo(String value) {
            addCriterion("db_type =", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeNotEqualTo(String value) {
            addCriterion("db_type <>", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeGreaterThan(String value) {
            addCriterion("db_type >", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeGreaterThanOrEqualTo(String value) {
            addCriterion("db_type >=", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeLessThan(String value) {
            addCriterion("db_type <", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeLessThanOrEqualTo(String value) {
            addCriterion("db_type <=", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeLike(String value) {
            addCriterion("db_type like", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeNotLike(String value) {
            addCriterion("db_type not like", value, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeIn(List<String> values) {
            addCriterion("db_type in", values, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeNotIn(List<String> values) {
            addCriterion("db_type not in", values, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeBetween(String value1, String value2) {
            addCriterion("db_type between", value1, value2, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbTypeNotBetween(String value1, String value2) {
            addCriterion("db_type not between", value1, value2, "dbType");
            return (Criteria) this;
        }

        public Criteria andDbRwIsNull() {
            addCriterion("db_rw is null");
            return (Criteria) this;
        }

        public Criteria andDbRwIsNotNull() {
            addCriterion("db_rw is not null");
            return (Criteria) this;
        }

        public Criteria andDbRwEqualTo(String value) {
            addCriterion("db_rw =", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwNotEqualTo(String value) {
            addCriterion("db_rw <>", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwGreaterThan(String value) {
            addCriterion("db_rw >", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwGreaterThanOrEqualTo(String value) {
            addCriterion("db_rw >=", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwLessThan(String value) {
            addCriterion("db_rw <", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwLessThanOrEqualTo(String value) {
            addCriterion("db_rw <=", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwLike(String value) {
            addCriterion("db_rw like", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwNotLike(String value) {
            addCriterion("db_rw not like", value, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwIn(List<String> values) {
            addCriterion("db_rw in", values, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwNotIn(List<String> values) {
            addCriterion("db_rw not in", values, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwBetween(String value1, String value2) {
            addCriterion("db_rw between", value1, value2, "dbRw");
            return (Criteria) this;
        }

        public Criteria andDbRwNotBetween(String value1, String value2) {
            addCriterion("db_rw not between", value1, value2, "dbRw");
            return (Criteria) this;
        }

        public Criteria andConUrlIsNull() {
            addCriterion("con_url is null");
            return (Criteria) this;
        }

        public Criteria andConUrlIsNotNull() {
            addCriterion("con_url is not null");
            return (Criteria) this;
        }

        public Criteria andConUrlEqualTo(String value) {
            addCriterion("con_url =", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlNotEqualTo(String value) {
            addCriterion("con_url <>", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlGreaterThan(String value) {
            addCriterion("con_url >", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlGreaterThanOrEqualTo(String value) {
            addCriterion("con_url >=", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlLessThan(String value) {
            addCriterion("con_url <", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlLessThanOrEqualTo(String value) {
            addCriterion("con_url <=", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlLike(String value) {
            addCriterion("con_url like", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlNotLike(String value) {
            addCriterion("con_url not like", value, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlIn(List<String> values) {
            addCriterion("con_url in", values, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlNotIn(List<String> values) {
            addCriterion("con_url not in", values, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlBetween(String value1, String value2) {
            addCriterion("con_url between", value1, value2, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConUrlNotBetween(String value1, String value2) {
            addCriterion("con_url not between", value1, value2, "conUrl");
            return (Criteria) this;
        }

        public Criteria andConIpIsNull() {
            addCriterion("con_ip is null");
            return (Criteria) this;
        }

        public Criteria andConIpIsNotNull() {
            addCriterion("con_ip is not null");
            return (Criteria) this;
        }

        public Criteria andConIpEqualTo(String value) {
            addCriterion("con_ip =", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpNotEqualTo(String value) {
            addCriterion("con_ip <>", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpGreaterThan(String value) {
            addCriterion("con_ip >", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpGreaterThanOrEqualTo(String value) {
            addCriterion("con_ip >=", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpLessThan(String value) {
            addCriterion("con_ip <", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpLessThanOrEqualTo(String value) {
            addCriterion("con_ip <=", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpLike(String value) {
            addCriterion("con_ip like", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpNotLike(String value) {
            addCriterion("con_ip not like", value, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpIn(List<String> values) {
            addCriterion("con_ip in", values, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpNotIn(List<String> values) {
            addCriterion("con_ip not in", values, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpBetween(String value1, String value2) {
            addCriterion("con_ip between", value1, value2, "conIp");
            return (Criteria) this;
        }

        public Criteria andConIpNotBetween(String value1, String value2) {
            addCriterion("con_ip not between", value1, value2, "conIp");
            return (Criteria) this;
        }

        public Criteria andConPortIsNull() {
            addCriterion("con_port is null");
            return (Criteria) this;
        }

        public Criteria andConPortIsNotNull() {
            addCriterion("con_port is not null");
            return (Criteria) this;
        }

        public Criteria andConPortEqualTo(Integer value) {
            addCriterion("con_port =", value, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortNotEqualTo(Integer value) {
            addCriterion("con_port <>", value, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortGreaterThan(Integer value) {
            addCriterion("con_port >", value, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("con_port >=", value, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortLessThan(Integer value) {
            addCriterion("con_port <", value, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortLessThanOrEqualTo(Integer value) {
            addCriterion("con_port <=", value, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortIn(List<Integer> values) {
            addCriterion("con_port in", values, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortNotIn(List<Integer> values) {
            addCriterion("con_port not in", values, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortBetween(Integer value1, Integer value2) {
            addCriterion("con_port between", value1, value2, "conPort");
            return (Criteria) this;
        }

        public Criteria andConPortNotBetween(Integer value1, Integer value2) {
            addCriterion("con_port not between", value1, value2, "conPort");
            return (Criteria) this;
        }

        public Criteria andConUsernameIsNull() {
            addCriterion("con_username is null");
            return (Criteria) this;
        }

        public Criteria andConUsernameIsNotNull() {
            addCriterion("con_username is not null");
            return (Criteria) this;
        }

        public Criteria andConUsernameEqualTo(String value) {
            addCriterion("con_username =", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameNotEqualTo(String value) {
            addCriterion("con_username <>", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameGreaterThan(String value) {
            addCriterion("con_username >", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("con_username >=", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameLessThan(String value) {
            addCriterion("con_username <", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameLessThanOrEqualTo(String value) {
            addCriterion("con_username <=", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameLike(String value) {
            addCriterion("con_username like", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameNotLike(String value) {
            addCriterion("con_username not like", value, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameIn(List<String> values) {
            addCriterion("con_username in", values, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameNotIn(List<String> values) {
            addCriterion("con_username not in", values, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameBetween(String value1, String value2) {
            addCriterion("con_username between", value1, value2, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConUsernameNotBetween(String value1, String value2) {
            addCriterion("con_username not between", value1, value2, "conUsername");
            return (Criteria) this;
        }

        public Criteria andConPasswordIsNull() {
            addCriterion("con_password is null");
            return (Criteria) this;
        }

        public Criteria andConPasswordIsNotNull() {
            addCriterion("con_password is not null");
            return (Criteria) this;
        }

        public Criteria andConPasswordEqualTo(String value) {
            addCriterion("con_password =", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordNotEqualTo(String value) {
            addCriterion("con_password <>", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordGreaterThan(String value) {
            addCriterion("con_password >", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("con_password >=", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordLessThan(String value) {
            addCriterion("con_password <", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordLessThanOrEqualTo(String value) {
            addCriterion("con_password <=", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordLike(String value) {
            addCriterion("con_password like", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordNotLike(String value) {
            addCriterion("con_password not like", value, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordIn(List<String> values) {
            addCriterion("con_password in", values, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordNotIn(List<String> values) {
            addCriterion("con_password not in", values, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordBetween(String value1, String value2) {
            addCriterion("con_password between", value1, value2, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConPasswordNotBetween(String value1, String value2) {
            addCriterion("con_password not between", value1, value2, "conPassword");
            return (Criteria) this;
        }

        public Criteria andConTimeoutIsNull() {
            addCriterion("con_timeout is null");
            return (Criteria) this;
        }

        public Criteria andConTimeoutIsNotNull() {
            addCriterion("con_timeout is not null");
            return (Criteria) this;
        }

        public Criteria andConTimeoutEqualTo(Integer value) {
            addCriterion("con_timeout =", value, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutNotEqualTo(Integer value) {
            addCriterion("con_timeout <>", value, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutGreaterThan(Integer value) {
            addCriterion("con_timeout >", value, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("con_timeout >=", value, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutLessThan(Integer value) {
            addCriterion("con_timeout <", value, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("con_timeout <=", value, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutIn(List<Integer> values) {
            addCriterion("con_timeout in", values, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutNotIn(List<Integer> values) {
            addCriterion("con_timeout not in", values, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("con_timeout between", value1, value2, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andConTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("con_timeout not between", value1, value2, "conTimeout");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalIsNull() {
            addCriterion("pool_maxtotal is null");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalIsNotNull() {
            addCriterion("pool_maxtotal is not null");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalEqualTo(Integer value) {
            addCriterion("pool_maxtotal =", value, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalNotEqualTo(Integer value) {
            addCriterion("pool_maxtotal <>", value, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalGreaterThan(Integer value) {
            addCriterion("pool_maxtotal >", value, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("pool_maxtotal >=", value, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalLessThan(Integer value) {
            addCriterion("pool_maxtotal <", value, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalLessThanOrEqualTo(Integer value) {
            addCriterion("pool_maxtotal <=", value, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalIn(List<Integer> values) {
            addCriterion("pool_maxtotal in", values, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalNotIn(List<Integer> values) {
            addCriterion("pool_maxtotal not in", values, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalBetween(Integer value1, Integer value2) {
            addCriterion("pool_maxtotal between", value1, value2, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxtotalNotBetween(Integer value1, Integer value2) {
            addCriterion("pool_maxtotal not between", value1, value2, "poolMaxtotal");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleIsNull() {
            addCriterion("pool_maxidle is null");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleIsNotNull() {
            addCriterion("pool_maxidle is not null");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleEqualTo(Integer value) {
            addCriterion("pool_maxidle =", value, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleNotEqualTo(Integer value) {
            addCriterion("pool_maxidle <>", value, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleGreaterThan(Integer value) {
            addCriterion("pool_maxidle >", value, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleGreaterThanOrEqualTo(Integer value) {
            addCriterion("pool_maxidle >=", value, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleLessThan(Integer value) {
            addCriterion("pool_maxidle <", value, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleLessThanOrEqualTo(Integer value) {
            addCriterion("pool_maxidle <=", value, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleIn(List<Integer> values) {
            addCriterion("pool_maxidle in", values, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleNotIn(List<Integer> values) {
            addCriterion("pool_maxidle not in", values, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleBetween(Integer value1, Integer value2) {
            addCriterion("pool_maxidle between", value1, value2, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMaxidleNotBetween(Integer value1, Integer value2) {
            addCriterion("pool_maxidle not between", value1, value2, "poolMaxidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleIsNull() {
            addCriterion("pool_minidle is null");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleIsNotNull() {
            addCriterion("pool_minidle is not null");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleEqualTo(Integer value) {
            addCriterion("pool_minidle =", value, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleNotEqualTo(Integer value) {
            addCriterion("pool_minidle <>", value, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleGreaterThan(Integer value) {
            addCriterion("pool_minidle >", value, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleGreaterThanOrEqualTo(Integer value) {
            addCriterion("pool_minidle >=", value, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleLessThan(Integer value) {
            addCriterion("pool_minidle <", value, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleLessThanOrEqualTo(Integer value) {
            addCriterion("pool_minidle <=", value, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleIn(List<Integer> values) {
            addCriterion("pool_minidle in", values, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleNotIn(List<Integer> values) {
            addCriterion("pool_minidle not in", values, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleBetween(Integer value1, Integer value2) {
            addCriterion("pool_minidle between", value1, value2, "poolMinidle");
            return (Criteria) this;
        }

        public Criteria andPoolMinidleNotBetween(Integer value1, Integer value2) {
            addCriterion("pool_minidle not between", value1, value2, "poolMinidle");
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