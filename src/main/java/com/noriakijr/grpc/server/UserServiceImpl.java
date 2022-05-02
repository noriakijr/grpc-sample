package com.noriakijr.grpc.server;

import com.noriakijr.grpc.AnyConnection;
import com.proto.user.*;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
        System.out.println("Creating User");
        User user = request.getUser();
        int userId = AnyConnection.insert(user.getName(), user.getEmail());

        System.out.println("Inserted user: " + userId);

        CreateUserResponse response = CreateUserResponse.newBuilder()
                .setUser(user.toBuilder().setId(String.valueOf(userId)).build())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserResponse> responseObserver) {
        String userID = request.getUserId();
        AnyConnection.deleteById(Integer.parseInt(userID));

        System.out.println("User was deleted");

        responseObserver.onNext(
                DeleteUserResponse.newBuilder()
                        .setUserId(userID)
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void listUser(ListUserRequest request, StreamObserver<ListUserResponse> responseObserver) {
        System.out.println("Streaming users");
        AnyConnection.findAll().forEach(it -> {
            String name = it.get("name");
            String email = it.get("email");

            responseObserver.onNext(
                    ListUserResponse.newBuilder()
                            .setUser(User.newBuilder()
                                    .setName(name)
                                    .setEmail(email)
                                    .build())
                            .build());
        });
        responseObserver.onCompleted();
    }
}
