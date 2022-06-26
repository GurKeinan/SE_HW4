import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread;

public class MyReentrantLock implements Lock, AutoCloseable{

    AtomicBoolean isLocked;
    int numOflocks;

    Thread ownerOfLock;


    public MyReentrantLock()
    {
        this.isLocked  = new AtomicBoolean();
        numOflocks = 0;
        ownerOfLock = null;
    }

    public void acquire()
    {

        while(!tryAcquire())
        {
            Thread.yield();
        }
    }

    public boolean tryAcquire()
    {
        boolean isLockfree = isLocked.compareAndSet(false, true);
        if(!isLockfree && ownerOfLock == Thread.currentThread())
        {
            numOflocks++;
            return true;
        }
        if(isLockfree)
        {
            this.ownerOfLock = Thread.currentThread();
            this.numOflocks=1;
            return true;
        }
        return false;
    }

    public void release() {
        try
        {
            if(Thread.currentThread() != ownerOfLock) throw new IllegalReleaseAttempt();
            else if(numOflocks > 1) numOflocks--;
            else
            {
                numOflocks = 0;
                ownerOfLock = null;
                isLocked.set(false);

            }
        }
        catch(IllegalReleaseAttempt e){throw new IllegalReleaseAttempt();}

    }

    public void close()
    {
        try{
            release();
        }
        catch(Exception e){throw new IllegalReleaseAttempt();}
    }
}