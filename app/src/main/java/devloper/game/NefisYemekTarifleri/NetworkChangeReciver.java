package devloper.game.NefisYemekTarifleri;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class NetworkChangeReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        internetBaglantisi(context);
    }

    boolean internetBaglantisi(Context context) {
        ConnectivityManager conMgr=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo()!=null && conMgr.getActiveNetworkInfo().isAvailable() &&
                conMgr.getActiveNetworkInfo().isConnected()){
            Toast.makeText(context,"İnternet Bağlantınız Aktiv",Toast.LENGTH_LONG).show();
            return true;
        }else {
            Toast.makeText(context,"İnternet Bağlantınız Kapalı",Toast.LENGTH_LONG).show();

            return  false;
        }



    }

}
