//package com.data.sewalaptop.service.implementation;
//
//import java.security.MessageDigest;
//import java.sql.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.data.sewalaptop.service.Userservice;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Service
//public class Userserviceimpl implements Userservice{
//    @Autowired
//    private Userrepository userrepository;
//
//    @Override
//    public List<User> index() {
//        List<User> index = userrepository.findAll();
//        return index;
//    }
//
//    @Override
//    public User register(User user) {
//        user.setPassword(passEncript(user.getPassword()));
//        return userrepository.save(user);
//    }
//
//    @Override
//    public User show(Long id) {
//        Optional<User> bOptional = userrepository.findById(id);
//        if (bOptional.isPresent()) {
//            return bOptional.get();
//        }
//        return null;
//    }
//
//    @Override
//    public User update(Long id, User user) {
//        User data = this.show(id);
//        if (data == null) {
//            return null;
//        }
//        data.setNama_depan(user.getNama_depan());
//        data.setNama_belakang(user.getNama_belakang());
//        data.setEmail(user.getEmail());
//        data.setPassword(passEncript(user.getPassword()));
//        data.setPosisi(user.getPosisi());
//
//        return data;
//    }
//
//    @Override
//    public User delete(Long id) {
//        User data = this.show(id);
//        if (data == null) {
//            return null;
//        }
//        userrepository.delete(data);
//        return data;
//    }
//
//    private String passEncript(String pass) {
//        MessageDigest md;
//        String passEnc;
//        try {
//            md = MessageDigest.getInstance("MD5");
//            md.update(pass.getBytes());
//            byte[] bytes = md.digest();
//            StringBuilder s = new StringBuilder();
//            for(int i=0; i< bytes.length ;i++)
//            {
//                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//            }
//            passEnc = s.toString();
//        } catch (Exception e) {
//            return "Password Encryption Error";
//        }
//        return passEnc;
//    }
//
//    @Override
//    public Userdto login(User dataRequest) throws Exception {
//        User usr = userrepository.findByEmail(dataRequest.getEmail());
//        if (usr == null) {
//            throw new Exception("Invalid email or password.");
//        }
//        String pas=passEncript(dataRequest.getPassword());
//        if (!pas.equals(usr.getPassword())){
//            throw new Exception("Invalid email or password.");
//        }
//
//        String token = Jwts.builder()
//        .setSubject(dataRequest.getEmail())
//        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token berlaku selama 24 jam
//        .signWith(SignatureAlgorithm.HS512, "SecretKey") // Ganti dengan secret key yang lebih aman
//        .compact();
//
//        return Userdto.builder().token(token).id(usr.getId()).nama_depan(usr.getNama_depan()).nama_belakang(usr.getNama_belakang()).email(usr.getEmail()).build();
//    }
//
//}
