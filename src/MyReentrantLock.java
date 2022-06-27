import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread;

public class MyReentrantLock implements Lock, AutoCloseable{

    AtomicBoolean isLocked;
    int numOflocks;

    Thread ownerOfLock;

    /**
     * constructor for new reentrant lock
     * isLocked initialized to false- the lock is unlocked in the start
     * numOfLocks- the number of times the owner locked the lock- initialized to 0- the lock is unlocked
     * ownerOfLock- the thread which currently locks the lock- initialized to 0- in the start the lock is unlocked
     */
    public MyReentrantLock()
    {
        this.isLocked  = new AtomicBoolean();
        numOflocks = 0;
        ownerOfLock = null;
    }


    /**
     * trying acquire until success- the thread performs yield when fails until he successfully locks the lock
     * heavily using tryAcquire method.
     */
    public void acquire()
    {

        while(!tryAcquire())
        {
            Thread.yield();
        }
    }

    /**
     * trying to lock the lock.
     * first we check if the current thread already is the owner- if he does he can automatically access without
     * waiting. if he enters again, we increase the numOfLock and return true.
     * if the thread isn't the owner, we checking if the lock is unlocked.
     * if he does, we updating the variables accordingly and return true, if he doesn't we return false
     * @return true if the Thread successfully locked the lock, false otherwise
     */
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

    /**
     * releasing lock.
     * @throws IllegalReleaseAttempt if thread who isn't the owner trying to release the lock.
     * if numOfLock is bigger than 1, which means the owner locked the lock multiple times, we decrease numOfLock but
     * the owner stays the same.
     * else, we set the owner to null and numOfLocks to 0.
     */
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

    /**
     * close the lock
     * @throws IllegalReleaseAttempt if the lock is unlocked
     * releasing all the locks and sets all the variable to their starting values.
     */
    public void close()
    {
        if(!isLocked.get())
            throw new IllegalReleaseAttempt();
        this.ownerOfLock = null;
        this.numOflocks = 0;
        this.isLocked.set(false);
    }
}