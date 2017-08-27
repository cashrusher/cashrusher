package com.rusher.yunbi.dto;

/**
 * Created by lichang on 14-2-26.
 */

public enum Symbol {
    btc {
        @Override
        public boolean isBtc() {
            return true;
        }

        @Override
        public boolean isLtc() {
            return false;
        }

        @Override
        public boolean isUsd() {
            return false;
        }

        @Override
        public boolean isEth() {
            return false;
        }

        @Override
        public boolean isCny() {
            return false;
        }
    }, ltc {
        @Override
        public boolean isBtc() {
            return false;
        }

        @Override
        public boolean isLtc() {
            return true;
        }

        @Override
        public boolean isUsd() {
            return false;
        }

        @Override
        public boolean isCny() {
            return false;
        }

        @Override
        public boolean isEth() {
            return false;
        }
    }, usd {
        @Override
        public boolean isBtc() {
            return false;
        }

        @Override
        public boolean isLtc() {
            return false;
        }

        @Override
        public boolean isUsd() {
            return true;
        }

        @Override
        public boolean isCny() {
            return false;
        }

        @Override
        public boolean isEth() {
            return false;
        }
    }, cny {
        @Override
        public boolean isBtc() {
            return false;
        }

        @Override
        public boolean isLtc() {
            return false;
        }

        @Override
        public boolean isUsd() {
            return false;
        }

        @Override
        public boolean isCny() {
            return true;
        }

        @Override
        public boolean isEth() {
            return false;
        }
    }, eth {
        @Override
        public boolean isBtc() {
            return false;
        }

        @Override
        public boolean isLtc() {
            return false;
        }

        @Override
        public boolean isUsd() {
            return false;
        }

        @Override
        public boolean isCny() {
            return false;
        }

        @Override
        public boolean isEth() {
            return true;
        }
    };

    public abstract boolean isBtc();

    public abstract boolean isLtc();

    public abstract boolean isUsd();

    public abstract boolean isCny();

    public abstract boolean isEth();

}

