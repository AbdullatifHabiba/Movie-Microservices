package com.moviecatalogservice.protobuf.java;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: trendingmovies.proto")
public final class TrendingMoviesServiceGrpc {

  private TrendingMoviesServiceGrpc() {}

  public static final String SERVICE_NAME = "TrendingMoviesService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Trendingmovies.TopMoviesRequest,
      Trendingmovies.TopMoviesResponse> METHOD_GET_TOP_MOVIES =
      io.grpc.MethodDescriptor.<Trendingmovies.TopMoviesRequest, Trendingmovies.TopMoviesResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "TrendingMoviesService", "GetTopMovies"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Trendingmovies.TopMoviesRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Trendingmovies.TopMoviesResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TrendingMoviesServiceStub newStub(io.grpc.Channel channel) {
    return new TrendingMoviesServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TrendingMoviesServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TrendingMoviesServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TrendingMoviesServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TrendingMoviesServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TrendingMoviesServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getTopMovies(Trendingmovies.TopMoviesRequest request,
        io.grpc.stub.StreamObserver<Trendingmovies.TopMoviesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_TOP_MOVIES, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_TOP_MOVIES,
            asyncUnaryCall(
              new MethodHandlers<
                Trendingmovies.TopMoviesRequest,
                Trendingmovies.TopMoviesResponse>(
                  this, METHODID_GET_TOP_MOVIES)))
          .build();
    }
  }

  /**
   */
  public static final class TrendingMoviesServiceStub extends io.grpc.stub.AbstractStub<TrendingMoviesServiceStub> {
    private TrendingMoviesServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrendingMoviesServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrendingMoviesServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrendingMoviesServiceStub(channel, callOptions);
    }

    /**
     */
    public void getTopMovies(Trendingmovies.TopMoviesRequest request,
        io.grpc.stub.StreamObserver<Trendingmovies.TopMoviesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_MOVIES, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TrendingMoviesServiceBlockingStub extends io.grpc.stub.AbstractStub<TrendingMoviesServiceBlockingStub> {
    private TrendingMoviesServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrendingMoviesServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrendingMoviesServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrendingMoviesServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Trendingmovies.TopMoviesResponse getTopMovies(Trendingmovies.TopMoviesRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_TOP_MOVIES, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TrendingMoviesServiceFutureStub extends io.grpc.stub.AbstractStub<TrendingMoviesServiceFutureStub> {
    private TrendingMoviesServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TrendingMoviesServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TrendingMoviesServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TrendingMoviesServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Trendingmovies.TopMoviesResponse> getTopMovies(
        Trendingmovies.TopMoviesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_TOP_MOVIES, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TOP_MOVIES = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TrendingMoviesServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TrendingMoviesServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TOP_MOVIES:
          serviceImpl.getTopMovies((Trendingmovies.TopMoviesRequest) request,
              (io.grpc.stub.StreamObserver<Trendingmovies.TopMoviesResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class TrendingMoviesServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Trendingmovies.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TrendingMoviesServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TrendingMoviesServiceDescriptorSupplier())
              .addMethod(METHOD_GET_TOP_MOVIES)
              .build();
        }
      }
    }
    return result;
  }
}
