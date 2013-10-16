package com.gracecode.RainNoise.serivce;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.gracecode.RainNoise.player.PlayerManager;

/**
 * Created with IntelliJ IDEA.
 * <p/>
 * User: mingcheng
 * Date: 13-10-11
 */
public class PlayerService extends Service {
    private static PlayerManager mPlayerManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayerManager = PlayerManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public PlayerService getService() {
            return PlayerService.this;
        }

        public PlayerManager getPlayersManager() {
            return mPlayerManager;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPlayerManager.isPlaying()) {
            mPlayerManager.stop();
        }
    }
}
