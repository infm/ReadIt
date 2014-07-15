package com.infm.readit;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.infm.readit.database.LastReadContentProvider;
import com.infm.readit.readable.MiniReadable;
import com.infm.readit.util.CachedFilesAdapter;

import java.util.ArrayList;

public class FileListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final String LOGTAG = "FileListFragment";

	private CachedFilesAdapter adapter;

	private TextView tvEmpty;
	private ListView listView;

    private ArrayList<MiniReadable> objectsContainer = new ArrayList<MiniReadable>();

	public FileListFragment(){}

    //TODO: remove in release v
	@Override
	public void onCreate(Bundle savedInstanceState){
		Log.d(LOGTAG, "onCreate() called");
        super.onCreate(savedInstanceState);
	}

    //TODO: remove in release v
    @Override
    public void onStart(){
        Log.d(LOGTAG, "onStart() called");
        super.onStart();
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d(LOGTAG, "onCreateView() called");
		View view = inflater.inflate(R.layout.fragment_file_list, container, false);
		findViews(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
        Log.d(LOGTAG, "onActivityCreated() called");
		super.onActivityCreated(savedInstanceState);
		initViews(getActivity());
		getLoaderManager().initLoader(0, null, this);
	}

	private void findViews(View v){
		listView = (ListView) v.findViewById(R.id.fileListView);
		tvEmpty = (TextView) v.findViewById(R.id.text_view_empty);
	}

	private void initViews(final Context context){
		adapter = new CachedFilesAdapter(context);
		listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){
                adapter.hideActionView();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
            }
        });

        listView.setEmptyView(tvEmpty);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args){
        Log.d(LOGTAG, "onCreateLoader() called"); //TODO: remove in release v
		return new CursorLoader(getActivity(), LastReadContentProvider.CONTENT_URI,
                null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        Log.d(LOGTAG, "onLoadFinished() called"); //TODO: remove in release v
        adapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader loader){
        Log.d(LOGTAG, "onLoaderReset() called"); //TODO: remove in release v
		adapter.swapCursor(null);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
	}
}
