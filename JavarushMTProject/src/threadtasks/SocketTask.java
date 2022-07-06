package threadtasks;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketTask<T> implements CancellableTask<T> {
    private Socket socket;

    protected synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }

    public synchronized void cancel() {
        try {
        	if(socket!=null) {
        		socket.close();} 
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {//возвращается новая таска в которую передается объект Сокет таск с переопределенным методом кансел - в котором пытается закрыть скоет, если не вышло то 
        	// то вызывает опять себя
            @SuppressWarnings("finally")
			public boolean cancel(boolean mayInterruptIfRunning) {
            	try {
            	SocketTask.this.cancel();}
            	finally {
            		 return super.cancel(mayInterruptIfRunning);
            	}

            }
        };
    }
}