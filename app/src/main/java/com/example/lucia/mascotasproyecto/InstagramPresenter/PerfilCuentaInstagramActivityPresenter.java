package com.example.lucia.mascotasproyecto.InstagramPresenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.Instagram.IPerfilCuentaInstagramActivityView;
import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.db.ConstructorUserInstagram;
import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.example.lucia.mascotasproyecto.pojo.UserInstagram;
import com.example.lucia.mascotasproyecto.restApi.ConstantesRestApi;
import com.example.lucia.mascotasproyecto.restApi.EndpointsApi;
import com.example.lucia.mascotasproyecto.restApi.adapter.RestApiAdapter;
import com.example.lucia.mascotasproyecto.restApi.model.LikeResponse;
import com.example.lucia.mascotasproyecto.restApi.model.MascotaResponse;
import com.example.lucia.mascotasproyecto.restApi.model.UserInstagramResponse;
import com.example.lucia.mascotasproyecto.restApiFirebase.EndpointsFirebase;
import com.example.lucia.mascotasproyecto.restApiFirebase.adapter.RestApiAdapterFirebase;
import com.example.lucia.mascotasproyecto.restApiFirebase.model.UsuarioLikeResponse;
import com.example.lucia.mascotasproyecto.restApiFirebase.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lucia.mascotasproyecto.MainActivity.IDUSERNAME;

/**
 * Created by Lucia on 06/06/2017.
 */

public class PerfilCuentaInstagramActivityPresenter implements IPerfilCuentaInstagramActivityPresentador{

    private IPerfilCuentaInstagramActivityView iPerfilCuentaInstagramActivityView;
    private Context context;
    private ConstructorUserInstagram constructorUserInstagram;
    private ArrayList<MascotaInstagram> mascotasInstagram;
    private ArrayList<UserInstagram> userInstagrams;

    public String Username = "";
    public String idUsername = "";
    public String idMedia = "";

    public PerfilCuentaInstagramActivityPresenter() {
        this.iPerfilCuentaInstagramActivityView = iPerfilCuentaInstagramActivityView;
        this.context = context;
        // obtenerMascotasCuentaInstagram(); Datos Dummy como si fuera a una BD
        // obtenerMediosRecientes();  Datos de Instagram de Self
        // el de abajo obtiene Media reciente por el ID

        if (MainActivity.ORIGEN == 1) {
            Username = MainActivity.USERNAME;
            obtenerIdbyUsername(Username);
        }else{
            if (MainActivity.ORIGEN == 2){
                idMedia = MainActivity.IDMEDIA;
                idUsername = IDUSERNAME;
                insertarLikeInstagrambymedia(idMedia);
                obtenerMediosRecientesbyId(idUsername);
            }
        }
    }


    public PerfilCuentaInstagramActivityPresenter(IPerfilCuentaInstagramActivityView iPerfilCuentaInstagramActivityView) {
        this.iPerfilCuentaInstagramActivityView = iPerfilCuentaInstagramActivityView;
       // obtenerMascotasCuentaInstagram();  Datos Dummy como si fuera a una BD
        // obtenerMediosRecientes();  Datos de Instagram de Self
        // el de abajo obtiene Media reciente por el ID

        if (MainActivity.ORIGEN == 1) {
            Username = MainActivity.USERNAME;
            obtenerIdbyUsername(Username);
        }else{
            if (MainActivity.ORIGEN == 2){
                idMedia = MainActivity.IDMEDIA;
                idUsername = IDUSERNAME;
                insertarLikeInstagrambymedia(idMedia);
                obtenerMediosRecientesbyId(idUsername);
            }
        }
     }

    @Override
    public void obtenerMascotasCuentaInstagram() {
        // Obtiene las mascotas con datos dummy como si las trajera de una base de datos
        constructorUserInstagram = new ConstructorUserInstagram(context);
        mascotasInstagram = constructorUserInstagram.obtenerDatos();
        mostrarMascotasRV();
    }
    @Override
    public void obtenerMediosRecientes() {

        // Obtiene los datos Media Recientes de la cuenta SELF
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                  MascotaResponse mascotaResponse = response.body();
                  mascotasInstagram = mascotaResponse.getMascotasInstagram();
                  mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión¡ Intenta de nuevo ", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION ", t.toString());
            }
        });
    }
    @Override
    public void obtenerIdbyUsername(String Username) {
        // Obtiene el Id de una cuenta buscandolo por el Username
        String url;
        url = ConstantesRestApi.KEY1_GET_ID_DE_USERNAME + Username + ConstantesRestApi.KEY3_GET_ID_DE_USERNAME;
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonIdbyUsername = restApiAdapter.construyeGsonDeserializadorIdbyUsername();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonIdbyUsername);
        Call<UserInstagramResponse> userInstagramResponseCall = endpointsApi.getIdbyUsername(url);
        userInstagramResponseCall.enqueue(new Callback<UserInstagramResponse>() {
            @Override
            public void onResponse(Call<UserInstagramResponse> call, Response<UserInstagramResponse> response) {
                UserInstagramResponse userInstagramResponse = response.body();
                userInstagrams = userInstagramResponse.getUserInstagrams();
                idUsername = userInstagrams.get(0).getId();
                IDUSERNAME=idUsername;
                obtenerMediosRecientesbyId(idUsername);
            }
            @Override
            public void onFailure(Call<UserInstagramResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión¡ Intenta de nuevo ", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION ", t.toString());
            }
        });
    }


    @Override
    public void obtenerMediosRecientesbyId(String idUsername) {
        // Obtiene los datos Media Recientes de la una cuenta buscandolo por id

        String url;

        url = ConstantesRestApi.KEY1_GET_USER_ID_MEDIA + idUsername + ConstantesRestApi.KEY4_GET_USER_ID_MEDIA;
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();

        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaid(url);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotasInstagram = mascotaResponse.getMascotasInstagram();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión¡ Intenta de nuevo ", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION ", t.toString());
            }
        });
    }
    @Override
    public void insertarLikeInstagrambymedia(final String idMedia) {
        String url;
        url = ConstantesRestApi.KEY1_POST_MEDIA_LIKE + idMedia + ConstantesRestApi.KEY3_POST_MEDIA_LIKE;
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonDarLike = restApiAdapter.construyeGsonDeserializadorDarLike();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonDarLike);
        Call<LikeResponse> likeResponseCall = endpointsApi.postlikeenmedia(url);
        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
                int codigo = likeResponse.getCode();

                // enviando like a la base de datos de FIrebase

                String token =  FirebaseInstanceId.getInstance().getToken();
                String id_usuario_instagram = IDUSERNAME;
                String id_foto_instagram = idMedia;
                enviarTokenLikeRegistro(token, id_usuario_instagram, id_foto_instagram);

                // Actualizar pantalla con el nuevo like

                idUsername = IDUSERNAME;
                obtenerMediosRecientesbyId(idUsername);
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión¡ Intenta de nuevo ", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION ", t.toString());
            }
        });



    }

    public void enviarTokenLikeRegistro(String token, String id_usuario_instagram, String id_foto_instagram){

        Log.d("TOKEN", token);
        Log.d("ID_USUARIO_INSTAGRAM", id_usuario_instagram);
        Log.d("ID_FOTO_INSTAGRAM", id_foto_instagram);

        RestApiAdapterFirebase restApiAdapterFirebase = new RestApiAdapterFirebase();
        EndpointsFirebase endpointsFirebase = restApiAdapterFirebase.establecerConexionRestApi();
        Call<UsuarioLikeResponse> usuarioLikeResponseCall = endpointsFirebase.registrarlikeusuario(token, id_usuario_instagram, id_foto_instagram);

        usuarioLikeResponseCall.enqueue(new Callback<UsuarioLikeResponse>() {
            @Override
            public void onResponse(Call<UsuarioLikeResponse> call, Response<UsuarioLikeResponse> response) {
                UsuarioLikeResponse usuarioLikeResponse = response.body();
                Log.d("ID_FIREBASE", usuarioLikeResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioLikeResponse.getId_dispositivo());
                Log.d("ID_USUARIO_INSTAGRAM", usuarioLikeResponse.getId_usuario_instagram());
                Log.d("ID_FOTO_INSTAGRAM", usuarioLikeResponse.getId_foto_instagram());

                toqueLikeAnimal();
            }

            @Override
            public void onFailure(Call<UsuarioLikeResponse> call, Throwable t) {

            }
        });

    }

    public void toqueLikeAnimal(){
        Log.d("TOQUE LIKE ANIMAL", "true");

        UsuarioResponse usuarioResponse = new UsuarioResponse("-KopfQiW6JdP2QRPPGim","",MainActivity.CUENTA_INSTAGRAM);

        RestApiAdapterFirebase restApiAdapterFirebase = new RestApiAdapterFirebase();
        EndpointsFirebase endpoints = restApiAdapterFirebase.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.toqueLikeAnimal(usuarioResponse.getId(), usuarioResponse.getId_usuario_instagram());
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 = response.body();
                Log.d("ID_FIREBASE", usuarioResponse1.getId());
                Log.d("ID_DISPOSITIVO", usuarioResponse1.getId_dispositivo());
                Log.d("ID_USUARIO_INSTAGRAM", usuarioResponse1.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iPerfilCuentaInstagramActivityView.llenaEncabezado(mascotasInstagram);
        iPerfilCuentaInstagramActivityView.inicializarAdaptadorRV(iPerfilCuentaInstagramActivityView.crearAdaptador(mascotasInstagram));
        iPerfilCuentaInstagramActivityView.generalGridLayout();


    }

}
