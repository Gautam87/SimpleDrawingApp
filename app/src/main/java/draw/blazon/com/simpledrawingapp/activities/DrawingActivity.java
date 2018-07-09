package draw.blazon.com.simpledrawingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import draw.blazon.com.simpledrawingapp.R;
import draw.blazon.com.simpledrawingapp.views.DrawingView;

public class DrawingActivity extends AppCompatActivity {

    @BindView(R.id.drawing_view)
    DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        ButterKnife.bind(this);

    }



}
