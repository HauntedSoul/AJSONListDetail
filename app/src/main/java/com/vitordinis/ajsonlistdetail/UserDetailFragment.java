package com.vitordinis.ajsonlistdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class UserDetailFragment extends Fragment {

    public static final String ARG_USER_JSON = "user";
    private static final String COMMENTS_URL = "https://jsonplaceholder.typicode.com/posts?userId=";

    public UserModel mItem;

    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_USER_JSON)) {
            // Pass user in json string
            UserDetailActivity activity = (UserDetailActivity) this.getActivity();
            Gson gson = new Gson();
            mItem = gson.fromJson(getArguments().getString(ARG_USER_JSON), UserModel.class);
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail, container, false);

        if (mItem != null) {
            StringRequest request = new StringRequest(COMMENTS_URL + mItem.id, new Response.Listener<String>() {


                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<MessageModel>>(){}.getType();
                    mItem.messages = gson.fromJson(response, listType);
                    View recyclerView = container.findViewById(R.id.user_list);
                    assert recyclerView != null;
                    setupRecyclerView((RecyclerView) recyclerView);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), "Error getting user list!", Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(request);
        }

        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new CommentsRecyclerViewAdapter(mItem.messages));
    }

    class CommentsRecyclerViewAdapter
            extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder> {

        private final List<MessageModel> mValues;

        CommentsRecyclerViewAdapter(List<MessageModel> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mTitleView.setText(String.valueOf(mValues.get(position).title));
            holder.mMessageView.setText(mValues.get(position).body);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mTitleView;
            final TextView mMessageView;
            MessageModel mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mTitleView = view.findViewById(R.id.title);
                mMessageView = view.findViewById(R.id.body);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTitleView.getText() + "'";
            }
        }
    }
}
