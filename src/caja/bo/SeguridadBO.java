/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja.bo;

import caja.dao.SeguridadDAO;
import caja.mapeo.entidades.Usuario;
import caja.mapeo.entidades.ValeAcademico;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author user
 */
public class SeguridadBO implements SeguridadBOIFace {

    private Usuario usuario;
    private String ip;
    private String mac;
//    private String urlReporte = "jdbc:sqlserver://localhost;databaseName=caja";
    private final String usuarioReporte = "sa";
    private final String passwordReporte = "4dm1n1str4c10nGOB90570";
    private final String urlReporte = "jdbc:sqlserver://192.168.1.67;databaseName=caja";
    private final String DIR_FACTURADOR = "\\\\192.168.1.67\\DATA\\";
//    private String DIR_FACTURADOR = "\\D:\\temp\\ccpa\\";
    private String NRO_CUENTA_DETRACCION = "00101150550";

    public Usuario getUsuario() {
        return usuario;
    }
    private static SeguridadBO INSTANCE = new SeguridadBO();

    public static void createInstance() {
        if (INSTANCE == null) {
            synchronized (SeguridadBO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SeguridadBO();
                }
            }
        }

    }

    public static SeguridadBO getInstance() {
        createInstance();
        return INSTANCE;
    }

    @Override
    public boolean AutenticarUsuario(String pusuario, byte[] clave) {
        SeguridadDAO aDAO = SeguridadDAO.getInstance();
        this.ObtenerMacIp();
        Usuario u = aDAO.EncontrarUsuario(pusuario, mac);
        if (u == null) {
            return false;
        }
        if (Arrays.equals(clave, u.getPassword())) {
            /*SeguridadDAO sDAO = SeguridadDAO.getInstance();
             sDAO.ObtenerNombreUsuario(u.getId());*/
            u.setPassword(null);
            usuario = u;
            return true;
        } else {
            u.setPassword(null);
            return false;
        }
    }

    public void ObtenerMacIp() {
        InetAddress ip_address;
        try {
            ip_address = InetAddress.getLocalHost();
            ip = ip_address.getHostAddress();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip_address);
            byte[] mac_address = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac_address.length; i++) {
                sb.append(String.format("%02X%s", mac_address[i], (i < mac_address.length - 1) ? "-" : ""));
            }
            this.mac = sb.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario InicializarDatosUsuario() {
        SeguridadDAO sDAO = SeguridadDAO.getInstance();
        usuario = sDAO.InicializarDatosUsuario(usuario);
        return usuario;
    }

    @Override
    public boolean CambiarContrasena(byte[] contrasena) {
        SeguridadDAO aDAO = SeguridadDAO.getInstance();
        this.usuario.setPassword(contrasena);
        return aDAO.CambiarContrasena(this.usuario);
    }

    @Override
    public Date ObtenerFechaServidor() {
        SeguridadDAO sDAO = SeguridadDAO.getInstance();
        return sDAO.ObtenerFechaServidor();
    }

    @Override
    public Date ObtenerFechaHoraServidor() {
        SeguridadDAO sDAO = SeguridadDAO.getInstance();
        return sDAO.ObtenerFechaHoraServidor();
    }

    @Override
    public Date SumaMesesFechaServidor(int cantMeses) {
        SeguridadDAO sDAO = SeguridadDAO.getInstance();
        return sDAO.SumaMesesFechaServidor(cantMeses);
    }

    @Override
    public void GenerarLog(String mensaje) {
        try {
            BufferedWriter bw = null;
            PrintWriter wr = null;
            File f;
            Date fechaSistema = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd");
            f = new File("log/log" + formato.format(fechaSistema) + ".txt");
            FileWriter w = new FileWriter(f, true);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH.mm.ss");
            wr.write("ERROR " + formatoHora.format(fechaSistema) + " " + mensaje + "\r\n");
            wr.close();
            bw.close();
        } catch (IOException e) {
        };
    }

    public String getUrlReporte() {
        return urlReporte;
    }

    @Override
    public Object CargarObjeto(String nombreClase, int id) {
        SeguridadDAO sDAO = SeguridadDAO.getInstance();
        return sDAO.CargarObjeto(nombreClase, id);
    }

    @Override
    public Set CargarListaValeAcademicoConsumos(ValeAcademico va) {
        SeguridadDAO sDAO = SeguridadDAO.getInstance();
        return sDAO.CargarListaValeAcademicoConsumos(va);
    }

    public String getIp() {
        return ip;
    }

    public String getMac() {
        return mac;
    }

    public String getUsuarioReporte() {
        return usuarioReporte;
    }

    public String getPasswordReporte() {
        return passwordReporte;
    }

    public static SeguridadBO getINSTANCE() {
        return INSTANCE;
    }

    public String getDIR_FACTURADOR() {
        return DIR_FACTURADOR;
    }

    public String getNRO_CUENTA_DETRACCION() {
        return NRO_CUENTA_DETRACCION;
    }

}
