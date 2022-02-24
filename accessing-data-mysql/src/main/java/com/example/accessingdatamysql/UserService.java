package com.example.accessingdatamysql;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repo) {
        userRepository = repo;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String createUser(String name, String email) {
        User u = new User(name, email);
        userRepository.save(u);
        return u.getId();
    }

    public int uploadUsers(MultipartFile multipartFile) {
        int count = 0;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            FileWriter fw = new FileWriter(new File("users.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                fw.write(line);
                count++;
            }
            fw.close();
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int uploadForm(UserUploadForm userUploadForm) {
        if (userUploadForm.getMultipartFile() != null
        && StringUtils.isNotBlank(userUploadForm.getName())
        && StringUtils.isNotBlank(userUploadForm.getComments())) {
            int count = 0;
            try {
                InputStream inputStream = userUploadForm.getMultipartFile().getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                FileWriter fw = new FileWriter(new File("users.csv"));
                while ((line = bufferedReader.readLine()) != null) {
                    fw.write(line);
                    count++;
                }
                fw.close();
                bufferedReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return count;
        }
        return -1;
    }
}
