package org.stonlexx.messenger.base.scheduler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.stonlexx.messenger.base.MessengerConstants;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public abstract class CommonScheduler implements Runnable {

    @Getter
    private final String identifier;

    public CommonScheduler() {
        this(RandomStringUtils.randomAlphanumeric(32));
    }


    /**
     * Отмена и закрытие потока
     */
    public void cancel() {
        MessengerConstants.SCHEDULER_MANAGER.cancelScheduler(identifier);
    }

    /**
     * Запустить асинхронный поток
     */
    public void runAsync() {
        MessengerConstants.SCHEDULER_MANAGER.runAsync(this);
    }

    /**
     * Запустить поток через определенное
     * количество времени
     *
     * @param delay - время
     * @param timeUnit - единица времени
     */
    public void runLater(long delay, TimeUnit timeUnit) {
        MessengerConstants.SCHEDULER_MANAGER.runLater(identifier, this, delay, timeUnit);
    }

    /**
     * Запустить цикличный поток через
     * определенное количество времени
     *
     * @param delay - время
     * @param period - период цикличного воспроизведения
     * @param timeUnit - единица времени
     */
    public void runTimer(long delay, long period, TimeUnit timeUnit) {
        MessengerConstants.SCHEDULER_MANAGER.runTimer(identifier, this, delay, period, timeUnit);
    }

}
