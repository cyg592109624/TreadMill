package com.sunrise.treadmill.views.workout.setting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunrise.treadmill.R;
import com.sunrise.treadmill.interfaces.workout.setting.OnKeyBoardReturn;
import com.sunrise.treadmill.utils.ImageUtils;

/**
 * Created by ChuHui on 2017/9/20.
 */

public class MyKeyBoardView extends LinearLayout {
    private ImageView title;
    private TextView showText;
    private OnKeyBoardReturn keyBoardReturn;

    public MyKeyBoardView(Context context) {
        this(context, null);
    }

    public MyKeyBoardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       LayoutInflater.from(context).inflate(R.layout.key_board, this, true);

        title = (ImageView) findViewById(R.id.key_board_title);
        showText = (TextView) findViewById(R.id.key_board_edit_value);

        findViewById(R.id.key_board_0).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_1).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_2).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_3).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_4).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_5).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_6).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_7).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_8).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_9).setOnClickListener(keyBoardClick);

        findViewById(R.id.key_board_del).setOnClickListener(keyBoardClick);
        findViewById(R.id.key_board_enter).setOnClickListener(keyBoardClick);

        findViewById(R.id.key_board_close).setOnClickListener(keyBoardClick);
    }

    private View.OnClickListener keyBoardClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String oldText = showText.getText().toString();
            switch (view.getId()) {
                default:
                    break;
                case R.id.key_board_0:
                    showText.setText(oldText + "0");
                    break;
                case R.id.key_board_1:
                    showText.setText(oldText + "1");
                    break;
                case R.id.key_board_2:
                    showText.setText(oldText + "2");
                    break;
                case R.id.key_board_3:
                    showText.setText(oldText + "3");
                    break;
                case R.id.key_board_4:
                    showText.setText(oldText + "4");
                    break;
                case R.id.key_board_5:
                    showText.setText(oldText + "5");
                    break;
                case R.id.key_board_6:
                    showText.setText(oldText + "6");
                    break;
                case R.id.key_board_7:
                    showText.setText(oldText + "7");
                    break;
                case R.id.key_board_8:
                    showText.setText(oldText + "8");
                    break;
                case R.id.key_board_9:
                    showText.setText(oldText + "9");
                    break;
                case R.id.key_board_del:
                    if (oldText.length() != 0) {
                        showText.setText(oldText.subSequence(0, oldText.length() - 1));
                    }
                    break;
                case R.id.key_board_enter:
                    if (keyBoardReturn != null) {
                        showText.setText("");
                        keyBoardReturn.onKeyBoardEnter(oldText);
                        keyBoardReturn.onKeyBoardClose();
                    }
                    break;
                case R.id.key_board_close:
                    if (keyBoardReturn != null) {
                        showText.setText("");
                        keyBoardReturn.onKeyBoardClose();
                    }
                    break;
            }
        }
    };

    public void setTitleImage(int imgResource) {
        ImageUtils.changeImageView(title, imgResource);
    }

    public void setKeyBoardReturn(@NonNull OnKeyBoardReturn onKeyBoardReturn) {
        keyBoardReturn = onKeyBoardReturn;
    }
}
