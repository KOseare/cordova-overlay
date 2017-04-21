package es.spaike.cordova.overlay;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Service;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

import android.provider.Settings;

public class Overlay extends CordovaPlugin {
    public static final String TAG = "Overlay";

    public Overlay() {
    }


    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
		final Activity thisActitity = this.cordova.getActivity();
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
				try {
					WindowManager wm = (WindowManager) thisActitity.getSystemService(Context.WINDOW_SERVICE);

					Button overlayedButton = new Button(thisActitity);
					overlayedButton.setText(args.getString(0));
					//overlayedButton.setOnTouchListener(thisActitity);
					overlayedButton.setAlpha(0.0f);
					overlayedButton.setBackgroundColor(0x55fe4444);
					//overlayedButton.setOnClickListener(thisActitity);

					WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
					params.gravity = Gravity.LEFT | Gravity.TOP;
					params.x = 0;
					params.y = 0;
					wm.addView(overlayedButton, params);

					View topLeftView = new View(thisActitity);
					WindowManager.LayoutParams topLeftParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, PixelFormat.TRANSLUCENT);
					topLeftParams.gravity = Gravity.LEFT | Gravity.TOP;
					topLeftParams.x = 0;
					topLeftParams.y = 0;
					topLeftParams.width = 0;
					topLeftParams.height = 0;
					wm.addView(topLeftView, topLeftParams);
					callbackContext.success("OK"); // Thread-safe.
				}
				catch (Exception ex) {
					callbackContext.success(ex.getMessage());
				}
            }
        });
        return true;
    }
}