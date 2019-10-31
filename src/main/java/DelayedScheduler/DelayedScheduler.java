package DelayedScheduler;

public interface DelayedScheduler {
	void delayedRun(long timeToRun, Runnable task);
}
