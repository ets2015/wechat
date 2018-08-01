package me.hao0.wechat.model.card;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 设置开卡字段接口
 * @author zJun
 * @date 2018年8月1日 下午5:03:44
 */
@Data
public class CardSet {
    private String card_id;
    private Service_statementBean service_statement;
    private Bind_old_cardBean bind_old_card;
    private Required_formBean required_form;
    private Optional_formBean optional_form;

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_id() {
        return this.card_id;
    }

    public Service_statementBean getService_statement() {
        return this.service_statement;
    }

    public Bind_old_cardBean getBind_old_card() {
        return this.bind_old_card;
    }

    public Required_formBean getRequired_form() {
        return this.required_form;
    }

    public Optional_formBean getOptional_form() {
        return this.optional_form;
    }

    public static class Service_statementBean {
        private String name;
        private String url;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }

    public static class Bind_old_cardBean {
        private String name;
        private String url;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }

    @Data
    @Builder
    public static class Required_formBean {
        private boolean can_modify;
        private List<Rich_field_listBean> rich_field_list;
        private List<Common_field_id_listBean> common_field_id_list;

        public void setCan_modify(boolean can_modify) {
            this.can_modify = can_modify;
        }

        public boolean getCan_modify() {
            return this.can_modify;
        }

        public void setRich_field_list(List<Rich_field_listBean> rich_field_list) {
            this.rich_field_list = rich_field_list;
        }

        public List<Rich_field_listBean> getRich_field_list() {
            return this.rich_field_list;
        }

        public void setCommon_field_id_list(List<Common_field_id_listBean> common_field_id_list) {
            this.common_field_id_list = common_field_id_list;
        }

        public List<Common_field_id_listBean> getCommon_field_id_list() {
            return this.common_field_id_list;
        }

        public static class Rich_field_listBean {
            private String type;
            private String name;
            private List<ValuesBean> values;

            public void setType(String type) {
                this.type = type;
            }

            public String getType() {
                return this.type;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }

            public void setValues(List<ValuesBean> values) {
                this.values = values;
            }

            public List<ValuesBean> getValues() {
                return this.values;
            }

            public static class ValuesBean {
            }
        }

        public static enum Common_field_id_listBean {
            /** 手机号 */
            USER_FORM_INFO_FLAG_MOBILE,
            /** 性别 */
            USER_FORM_INFO_FLAG_SEX,
            /** 姓名 */
            USER_FORM_INFO_FLAG_NAME,
            /** 生日 */
            USER_FORM_INFO_FLAG_BIRTHDAY,
            /** 身份证 */
            USER_FORM_INFO_FLAG_IDCARD,
            /** 邮箱 */
            USER_FORM_INFO_FLAG_EMAIL,
            /** 详细地址 */
            USER_FORM_INFO_FLAG_LOCATION,
            /** 教育背景 */
            USER_FORM_INFO_FLAG_EDUCATION_BACKGRO,
            /** 行业 */
            USER_FORM_INFO_FLAG_INDUSTRY,
            /** 收入 */
            USER_FORM_INFO_FLAG_INCOME,
            /** 兴趣爱好 */
            USER_FORM_INFO_FLAG_HABIT
        }
    }

    public static class Optional_formBean {
        private boolean can_modify;
        private List<Common_field_id_listBean> common_field_id_list;
        private List<Custom_field_listBean> custom_field_list;

        public void setCan_modify(boolean can_modify) {
            this.can_modify = can_modify;
        }

        public boolean getCan_modify() {
            return this.can_modify;
        }

        public void setCommon_field_id_list(List<Common_field_id_listBean> common_field_id_list) {
            this.common_field_id_list = common_field_id_list;
        }

        public List<Common_field_id_listBean> getCommon_field_id_list() {
            return this.common_field_id_list;
        }

        public void setCustom_field_list(List<Custom_field_listBean> custom_field_list) {
            this.custom_field_list = custom_field_list;
        }

        public List<Custom_field_listBean> getCustom_field_list() {
            return this.custom_field_list;
        }

        public static class Common_field_id_listBean {
        }

        public static class Custom_field_listBean {
        }
    }
}

/**
 * 
 * { "card_id":"pbLatjnrwUUdZI641gKdTMJzHGfc", "service_statement":{
 * "name":"会员守则", "url":"https://www.qq.com" }, "bind_old_card":{
 * "name":"老会员绑定", "url":"https://www.qq.com" }, "required_form":{
 * "can_modify":false, "rich_field_list":[ { "type":"FORM_FIELD_RADIO",
 * "name":"兴趣", "values":[ "钢琴", "舞蹈", "足球" ] }, { "type":"FORM_FIELD_SELECT",
 * "name":"喜好", "values":[ "郭敬明", "韩寒", "南派三叔" ] }, {
 * "type":"FORM_FIELD_CHECK_BOX", "name":"职业", "values":[ "赛车手", "旅行家" ] } ],
 * "common_field_id_list":[ "USER_FORM_INFO_FLAG_MOBILE" ] }, "optional_form":{
 * "can_modify":false, "common_field_id_list":[ "USER_FORM_INFO_FLAG_LOCATION",
 * "USER_FORM_INFO_FLAG_BIRTHDAY" ], "custom_field_list":[ "喜欢的电影" ] } }
 * 
 */