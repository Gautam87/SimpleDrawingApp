package draw.blazon.com.simpledrawingapp.activities;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import draw.blazon.com.simpledrawingapp.R;
import draw.blazon.com.simpledrawingapp.models.ExportModel;
import draw.blazon.com.simpledrawingapp.models.SquareItem;
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
    @BindView(R.id.iv_export)
    ImageView ivExport;
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
                mColorPicker.dismiss();
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

    @OnClick(R.id.iv_export)
    public void onExportClicked() {
        checkPermissions();

    }

    private void checkPermissions() {
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission, you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (drawingView.getSquareItems() != null && drawingView.getSquareItems().size() > 0) {
                writeStringAsFile(mapSquareToExport(drawingView.getSquareItems()), "SquareCoordinates.txt");
            } else {
                Toast.makeText(DrawingActivity.this, "No Squares found.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(DrawingActivity.this, "Permission Denied\n" +
                    deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }

    };

    private String mapSquareToExport(ArrayList<SquareItem> squareItems) {
        ArrayList<ExportModel> exportModels = new ArrayList<>();
        for (SquareItem squareItem : squareItems) {
            exportModels.add(new ExportModel(squareItem.getCoordinates()));
        }
        return exportModels.toString();
    }

    public void writeStringAsFile(final String fileContents, String fileName) {
        Context context = this.getApplicationContext();
        try {
            FileWriter out = new FileWriter(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName));
            out.write(fileContents);
            out.close();
            Toast.makeText(context, "File has been exported to " + Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/" + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
        }
    }
}
