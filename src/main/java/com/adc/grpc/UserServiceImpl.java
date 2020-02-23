package com.adc.grpc;

import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUserByUsername(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse response = UserResponse.newBuilder()
                .setUsername(request.getUsername())
                .setRealname(request.getUsername().toUpperCase())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
