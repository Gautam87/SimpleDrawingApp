package draw.blazon.com.simpledrawingapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import draw.blazon.com.simpledrawingapp.R;
import draw.blazon.com.simpledrawingapp.views.DrawingView;

public class DrawingActivity extends AppCompatActivity {

    private static final String TAG = DrawingActivity.class.getSimpleName();

    @BindView(R.id.drawing_view)
    DrawingView drawingView;
    @BindView(R.id.seekBar_size)
    AppCompatSeekBar seekBarSize;
    @BindView(R.id.iv_color_picker)
    ImageView ivColorPicker;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    private ColorPicker mColorPicker;

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

        mColorPicker = new ColorPicker(DrawingActivity.this, 255, 0, 0, 0);
        mColorPicker.setCallback(new ColorPickerCallback() {
            @Override
            public void onColorChosen(int color) {
                drawingView.setSelectedColor(color);
                mColorPicker.hide();
            }
        });
    }


    @OnClick(R.id.iv_color_picker)
    public void showColorPicker() {
        if (mColorPicker != null) {
            mColorPicker.show();
        }
    }


    @OnClick(R.id.iv_delete)
    public void onDeleteClicked() {
        new MaterialDialog.Builder(this)
                .title(R.string.confirmation)
                .content(R.string.delete_confirmation)
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        drawingView.resetDrawing();
                    }
                })
                .negativeText(R.string.disagree)
                .show();
    }
}
