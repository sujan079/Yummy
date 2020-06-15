package com.example.yummy.ui.view.user;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.yummy.R;
import com.example.yummy.data.database.YummyRoomDatabase;
import com.example.yummy.data.model.User;
import com.example.yummy.ui.view.viewmodel.MainActivityViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    FirebaseAuth firebaseAuth;
    private Button signOutButton;
    private ImageView ivProfile, ivUploadImage;
    private TextView tvName, tvEmail;
    private Button btnSave;
    private User currentUser;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    private StorageReference mStorageRef;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("USERS");

    private EditText etUsername, etEmail, etPhone;
    private MainActivityViewModel mainActivityViewModel;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivityViewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        firebaseAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        init();
    }

    public void init() {
        signOutButton = getView().findViewById(R.id.btn_logout);
        ivProfile = getView().findViewById(R.id.iv_profile);
        tvEmail = getView().findViewById(R.id.tv_email);
        tvName = getView().findViewById(R.id.tv_name);
        btnSave = getView().findViewById(R.id.btn_save);
        ivUploadImage = getView().findViewById(R.id.iv_upload_image);

        etUsername = getView().findViewById(R.id.et_username);
        etEmail = getView().findViewById(R.id.et_email);
        etPhone = getView().findViewById(R.id.et_phone);


        YummyRoomDatabase.getInstance(getContext());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(currentUser);
            }
        });


        getUser(firebaseAuth.getCurrentUser());

        ivUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.showBottomNavigation.setValue(false);
                firebaseAuth.signOut();
                NavController navController = Navigation.findNavController(getView());
                navController.navigate(R.id.action_userFragment_to_getStartedFragment);
            }
        });


    }

    public void getUser(FirebaseUser firebaseUser) {
        userRef.document(firebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    User db_user = documentSnapshot.toObject(User.class);
                    setProfile(db_user);
                    currentUser = db_user;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void setProfile(User user) {
        tvEmail.setText(user.getEmail());
        tvName.setText(user.getUsername());

        etEmail.setText(user.getEmail());
        etUsername.setText(user.getUsername());
        etPhone.setText(user.getPhone());

        Glide
                .with(ivProfile)
                .load(user.getImageUrl())
                .placeholder(R.drawable.dummy)
                .into(ivProfile);
    }

    public void update(User user) {
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();

        user.setEmail(email);
        user.setUsername(username);
        user.setPhone(phone);

        userRef.document(user.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileRefrence = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));

            fileRefrence.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileRefrence.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    currentUser.setImageUrl(uri.toString());
                                    update(currentUser);
                                }
                            });
                        }
                    });


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            uploadFile();

        }

    }
}
