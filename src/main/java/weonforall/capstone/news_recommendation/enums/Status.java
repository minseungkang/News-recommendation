package weonforall.capstone.news_recommendation.enums;

public class Status {

    public enum Obj {
        REQUEST(00) {
            @Override
            public String toString() {
                return "request";
            }
        },
        USER(10) {
            @Override
            public String toString() { return "user"; }
        },
        ARTICLE(11) {
            @Override
            public String toString() { return "article"; }
        },
        ARTICLE_HISTORY(12) {
            @Override
            public String toString() { return "article history"; }
        },
        FEATURE(13) {
            @Override
            public String toString() { return "feature"; }
        },
        INTEREST(14) {
            @Override
            public String toString() { return "interest"; }
        },
        KEYWORD(15) {
            @Override
            public String toString() { return "keyword"; }
        },
        PARAM(98) {
            @Override
            public String toString() { return "param"; }
        },
        ERROR(99) {
            @Override
            public String toString() { return "error"; }
        };

        private int value;
        Obj(int value) {
            this.value = value;
        }

        public int getValue() { return value; }
    }

    public enum Key {
        NOT_EXIST(10) {
            @Override
            public String toString() {
                return "not exist ";
            }
        },
        INVALID(11) {
            @Override
            public String toString() {
                return "invalid ";
            }
        },
        SUCCEED(98) {
            @Override
            public String toString() {
                return "succeed ";
            }
        },
        UNKNOWN(99) {
            @Override
            public String toString() {
                return "unknown ";
            }
        };

        private int value;
        Key(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}