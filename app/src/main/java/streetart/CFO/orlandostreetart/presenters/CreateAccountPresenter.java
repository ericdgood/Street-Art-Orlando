package streetart.CFO.orlandostreetart.presenters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import streetart.CFO.orlandostreetart.PreferenceManager;
import streetart.CFO.orlandostreetart.models.APIError;
import streetart.CFO.orlandostreetart.models.Auth;
import streetart.CFO.orlandostreetart.network.ErrorUtils;
import streetart.CFO.orlandostreetart.views.FragmentViews.MainActivity;

import static streetart.CFO.orlandostreetart.Constants.SERVICE;


/**
 * Created by Eric on 3/25/2019.
 */
public class CreateAccountPresenter {

    private static final String TAG = "CreateAccountPresenter";
    private Context context;
    private String email;
    private String emailConfirm;
    private String nickname;
    private String password;

    public CreateAccountPresenter(Context context) {
        this.context = context;
    }

    public void getStringInputs(EditText etEmail, EditText etEmailConfirm, EditText etNickName, EditText etPassword) {
        email = etEmail.getText().toString();
        emailConfirm = etEmailConfirm.getText().toString();
        nickname = etNickName.getText().toString();
        password = etPassword.getText().toString();
    }

    public Boolean checkForEmpty(EditText etEmail, EditText etEmailConfirm, EditText etNickName,
                                 EditText etPassword, Switch swAgreeTerms, Button btnAgreeTerms) {
        if (email.equals("")) {
            etEmail.setError("No email entered");
            return false;
        }
        if (emailConfirm.equals("")) {
            etEmailConfirm.setError("No confirmation email entered");
            return false;
        }
        if (nickname.equals("")) {
            etNickName.setError("No nickname entered");
            return false;
        }
        if (password.equals("")) {
            etPassword.setError("No password entered");
            return false;
        }
        if (!swAgreeTerms.isChecked()) {
            btnAgreeTerms.setError("Please switch on");
            return false;
        }
        return true;
    }


    public boolean checkEmails(EditText etEmail, EditText etEmailConfirm) {
//        Check for valid email
        if (!isValidEmail(email)) {
            etEmail.setError("Email is not valid");
            return false;
        }
//        Check for matching emails
        if (!email.equals(emailConfirm)) {
            etEmailConfirm.setError("Emails do not match");
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void postRegisterUser() {
        Call<Auth> call = SERVICE.postUserRegister(nickname, email, password);

        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()) {
//                    Added user successfully
                    Toast.makeText(context, "Welcome!!", Toast.LENGTH_SHORT).show();
                    getAuthToken();
                } else {
//                    Error adding new user
//                    todo: show errors messages
                    APIError error = ErrorUtils.parseError(response);
//                    Toast.makeText(createAccountView, error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Toast.makeText(context, "Error :(", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: ");
            }
        });

    }

    private void getAuthToken() {
        final PreferenceManager preferenceManager = new PreferenceManager(context);

        Call<Auth> call = SERVICE.postUserAuthKey(email, password);

        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                assert response.body() != null;
                if (!response.body().getAuthToken().equals(""))
                preferenceManager.saveAuthBoolean(true, response.body().getAuthToken(), email);
                Intent returnHome = new Intent(context, MainActivity.class);
                context.startActivity(returnHome);
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Log.i(TAG, "onFailure: GET_AUTH_TOKEN");
            }
        });
    }
}
