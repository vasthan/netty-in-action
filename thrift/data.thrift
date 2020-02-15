namespace java com.adc.thrift

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean

struct User {
    1: optional string username,
    2: optional int age,
    3: optional boolean married
}

exception UserException {
    1: optional int code,
    2: optional string message
}

service UserService {
    User getByUsername(1: required string username) throws (1: UserException ex),

    void save(1: required User user) throws (1: UserException ex)
}