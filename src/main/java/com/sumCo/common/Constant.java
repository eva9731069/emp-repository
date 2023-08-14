package com.sumCo.common;

/**
 * @author oplus
 * @Description: TODO(常量)
 * @date 2017-6-23 15:07
 */
public class Constant {

    /**
     * 超級管理員ID
     */
	public static final int SUPER_ADMIN = 1;

    /**
     * utf-8編碼
     */
	public static final String ENCODING_UTF_8="UTF-8";

    /**
     * X-Token sys請求的token
     */
    public static  final String X_TOKEN="X-Token";
    /**
     * 菜單類型
     */
    public enum MenuType {
        /**
         * 目錄
         */
    	CATALOG(0),
        /**
         * 菜單
         */
        MENU(1),
        /**
         * 按鈕
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 系統配置 狀態
     */
    public enum ConfigStatus {
        /**
         * 隱藏
         */
        HIDDEN(0),
        /**
         * 顯示
         */
        SHOW(1);

        private int value;

        ConfigStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 用戶 狀態
     */
    public enum UserStatus {
        /**
         * 禁用
         */
        DISABLE(0),
        /**
         * 正常
         */
        NORMAL(1);

        private int value;

        UserStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
