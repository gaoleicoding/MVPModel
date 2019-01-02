package com.gaolei.mvpmodel.base.mmodel;

import com.google.gson.Gson;

import java.util.List;

public class FilterTxtEntity {


    /**
     * code : 200
     * msg : ok
     * result : {"taskId":"079560a6c9f34783bdce47e168510038","action":2,"labels":[{"label":100,"level":2,"details":{"hint":["xxx","ooo"]}}]}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public static FilterTxtEntity objectFromData(String str) {

        return new Gson().fromJson(str, FilterTxtEntity.class);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "taskId='" + taskId + '\'' +
                    ", action=" + action +
                    ", labels=" + labels +
                    '}';
        }

        /**
         * taskId : 079560a6c9f34783bdce47e168510038
         * action : 2
         * labels : [{"label":100,"level":2,"details":{"hint":["xxx","ooo"]}}]
         */

        private String taskId;
        private int action;
        private List<LabelsBean> labels;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public static class LabelsBean {
            @Override
            public String toString() {
                return "LabelsBean{" +
                        "label=" + label +
                        ", level=" + level +
                        ", details=" + details +
                        '}';
            }

            /**
             * label : 100
             * level : 2
             * details : {"hint":["xxx","ooo"]}
             */

            private int label;
            private int level;
            private DetailsBean details;

            public static LabelsBean objectFromData(String str) {

                return new Gson().fromJson(str, LabelsBean.class);
            }

            public int getLabel() {
                return label;
            }

            public void setLabel(int label) {
                this.label = label;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public DetailsBean getDetails() {
                return details;
            }

            public void setDetails(DetailsBean details) {
                this.details = details;
            }

            public static class DetailsBean {
                private List<String> hint;

                public static DetailsBean objectFromData(String str) {

                    return new Gson().fromJson(str, DetailsBean.class);
                }

                public List<String> getHint() {
                    return hint;
                }

                public void setHint(List<String> hint) {
                    this.hint = hint;
                }
            }
        }
    }
}
