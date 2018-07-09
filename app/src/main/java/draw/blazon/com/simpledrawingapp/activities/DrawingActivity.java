package draw.blazon.com.simpledrawingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import draw.blazon.com.simpledrawingapp.R;
import draw.blazon.com.simpledrawingapp.views.DrawingView;

public class DrawingActivity extends AppCompatActivity {

    @BindView(R.id.drawing_view)
    DrawingView drawingView;
    @BindView(R.id.seekBar_size)
    AppCompatSeekBar seekBarSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initViews();

    }

    private void initViews() {
        seekBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (drawingView != null) {
                    drawingView.setSize(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
