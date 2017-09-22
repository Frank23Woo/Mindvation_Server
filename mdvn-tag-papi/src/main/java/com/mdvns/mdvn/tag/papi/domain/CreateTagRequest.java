package com.mdvns.mdvn.tag.papi.domain;

import org.springframework.stereotype.Component;

/**
     * 創建標簽
     */
    @Component
    public class CreateTagRequest {

        /* 新建標簽名稱*/
        private String name;

        /* 新建标签人的编号*/
        private Integer  creatorId;

        /* 標簽色值*/
        private String color;

        /* 備注*/
        private String remarks;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Integer creatorId) {
            this.creatorId = creatorId;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        @Override
    public String toString() {
        return "CreateTagRequest{" +
                "name='" + name + '\'' +
                ", creatorId=" + creatorId +
                ", color='" + color + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
