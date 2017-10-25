package com.lawyee.ceshi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSubmit;
    public static final String TAG = "==";
    private TextView mTvEmoji;
    private EditText mEtInputEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindTextViewEmoji();
    }

    private void bindTextViewEmoji() {
        InputFilter[] emojiFilters = {emojiFilter};
        mEtInputEmoji.setFilters(emojiFilters);
        mEtInputEmoji.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                mTvEmoji.setText(s);
            }
        });
    }

    InputFilter emojiFilter = new InputFilter() {
//        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            Matcher emojiMatcher = emoji.matcher(charSequence);
            if (emojiMatcher.find()) {
                Toast.makeText(MainActivity.this, "不支持输入表情", Toast.LENGTH_SHORT).show();
                return "";
            }
            return null;
        }
    };

    InputFilter inputFilter=new InputFilter() {

        //        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]|[[:punct:]]");
        Pattern pattern = Pattern.compile("^[\\u4E00-\\u9FA5A-Za-z0-9]+$");
        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            Matcher matcher= pattern.matcher(charSequence);
            if(matcher.find()){
                Toast.makeText(MainActivity.this, "不支持输入表情", Toast.LENGTH_SHORT).show();
                return "";
            }else{
                return null;
            }

        }
    };


        private void initView() {
            mBtnSubmit = (Button) findViewById(R.id.btn_submit);

            mBtnSubmit.setOnClickListener(this);
            mTvEmoji = (TextView) findViewById(R.id.tv_emoji);
            mTvEmoji.setOnClickListener(this);
            mEtInputEmoji = (EditText) findViewById(R.id.et_input_emoji);
            mEtInputEmoji.setOnClickListener(this);
        }


    @Override
    public void onClick(View view) {

    }
}
