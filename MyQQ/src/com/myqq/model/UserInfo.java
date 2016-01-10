package com.myqq.model;

import java.util.List;

public class UserInfo{

	
        //Í·Ïñ
        private String head;
        //Ãû×Ö
        private String name;
        //Ç©Ãû
        private String desc;
    
		public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

       

        public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		@Override
        public String toString() {
            return "UserInfo [head=" + head + ", name=" + name + ", signature="
                    + desc + "]";
        }
    
}
