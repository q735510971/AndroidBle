package com.e.ble.receiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.e.ble.receiver.listener.BLEReceiverListener;
import com.e.ble.util.BLELog;

/**
 * @author xiaoyunfei
 * @date: 2017/3/15
 * @Description：
 */

public class BLEStateReceiver extends BroadcastReceiver {

	private static BLEReceiverListener mBLEReceiverListener = null;

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {

			int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
					BluetoothAdapter.ERROR);
			switch (state) {

				case BluetoothAdapter.STATE_OFF:
					BLELog.w("BLEStateReceiver-->>STATE_OFF 手机蓝牙关闭");
					if (mBLEReceiverListener == null) {
						return;
					}
					mBLEReceiverListener.onStateOff();
					break;
				case BluetoothAdapter.STATE_TURNING_OFF:
					BLELog.w("BLEStateReceiver-->>STATE_TURNING_OFF 手机蓝牙正在关闭");
					break;
				case BluetoothAdapter.STATE_ON:
					BLELog.w("BLEStateReceiver-->>STATE_ON 手机蓝牙开启");
					if (mBLEReceiverListener == null) {
						return;
					}
					mBLEReceiverListener.onStateOn();
					break;
				case BluetoothAdapter.STATE_TURNING_ON:
					BLELog.w("BLEStateReceiver-->>STATE_TURNING_ON 手机蓝牙正在开启");
					break;
			}
		}
	}


	public static void setBLEReceiverListener(BLEReceiverListener bleReceiverListener) {
		mBLEReceiverListener = bleReceiverListener;
	}
}
