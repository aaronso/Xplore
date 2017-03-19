package xplore.xplore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by aaron on 2017-03-18.
 */

public class ImageAdapter extends BaseAdapter {
    //private Context mContext;
    private LayoutInflater inflater;

    public ImageAdapter(Context c) {
        inflater = LayoutInflater.from(c);
        //mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }



    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();

            holder.thumbTrail = (ImageView) convertView.findViewById(R.id.hikePic);
            holder.nameTrail =  (TextView) convertView.findViewById(R.id.hikeName);
            holder.trailDiff = (TextView) convertView.findViewById(R.id.hikeDiff);
            holder.locTrail = (TextView) convertView.findViewById(R.id.hikeLoc);
            holder.lengthTrail = (TextView) convertView.findViewById(R.id.hikeLen);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.thumbTrail.setImageResource(mThumbIds[position]);
        return convertView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,
            R.drawable.sample,


    };

    static class ViewHolder {
        ImageView thumbTrail;
        TextView nameTrail;
        TextView trailDiff;
        TextView locTrail;
        TextView lengthTrail;
    }


}
