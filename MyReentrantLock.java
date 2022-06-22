import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread;

public class MyReentrantLock implements Lock, AutoCloseable{

    AtomicBoolean isLocked;
    long pid;
    AtomicBoolean isAddingThread;
    ArrayList<Long> arr;

    public MyReentrantLock(){
        this.isLocked  = new AtomicBoolean();
        this.isAddingThread = new AtomicBoolean();
        ArrayList<Long> arr = new ArrayList<Long>();
        pid = -1;
    }

    public void acquire()
    {
        while(isAddingThread.compareAndSet(false, true))
        {
            this.arr.add(Thread.currentThread().getId());
        }
        isAddingThread.set(false);

        /*if(isLocked.compareAndSet(false , true) == true)
        {
            this.pid = Thread.currentThread().getId();
            this.arr.add(pid);
        }*/
    }

    public boolean tryAcquire() {
        if(isLocked.compareAndSet(false, true)){
            acquire();
            return true;
        }
        return false;
    }

    public void release() {
        try{
            
        }

    }

    public void close() {

    }
}