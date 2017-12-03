package com.animatededittext;

import android.text.Editable;

/**
 * Created by akash on 03-12-2017.
 */

public interface Watcher {
    void beforeTextChanged(CharSequence charSequence, int start, int count, int after);

    void onTextChanged(CharSequence charSequence, int start, int before, int count);

    void afterTextChanged(Editable editable);
}
