package com.myqq.model;

import java.util.List;

public class UserInfo{

	
        //ͷ��
        private String head;
        //����
        private String name;
        //ǩ��
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
