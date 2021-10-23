package br.com.digital.demoproduct.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class AppConfig {
 
	@Value("${spring.cloud.gcp.config_projectId}")
	private String projectId;
	
	@Bean
	public Credentials credential() {
		Credentials credential = null;
		InputStream in = null;
		String source = "{\r\n"
				+ "  \"type\": \"service_account\",\r\n"
				+ "  \"project_id\": \"loja-angular-de706\",\r\n"
				+ "  \"private_key_id\": \"0f6900bb1dec9ee46ffad4c70f88a13823da1f5d\",\r\n"
				+ "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDLtuPD79R+0tbS\\ny/XLn5bF6ellOD/H8I81+VkRVTynonGfVVqQYzaEuxMqha7o9sT5WB3tJf7QZUfL\\n8j/HE8IrauNbN77HCYKBPVCn+K6jGwZ9xHGn/qJemv9uYoA2aZJR3SOlO0oP+XgU\\nyN5H+3S0e1m0F518XNStihtfllt00gK+uuG94JTg1gCV4/eDFQIcbZ7/OgBT522g\\n7e0sCZ0ffKhvtYX50bCwDNSVoeNvrj8qY3zHov9veUjMEfdzlAaVA/K6fWJ1Fo1M\\nTaOO4Kaf8qftSsG1GLt5Hdg2r9D165H961IImOHOKYTHNYqvbq4M7f2CGvhC41O7\\nS8wFpcynAgMBAAECggEANurdeQ9Y/j2Riy9P2m5x43XdJRvmWdy4hsjrIXiUiu6I\\nafdFHGKaqmqSTqbOUeBgloWSP8G78Ws0UT9DwUPK1xCQLSGYjSi7pWKlsTqhvbfF\\n5rbEGxFSrWVkkdn9DW4lc2tFtkAHBcpvfE7wWYBo5FTSiHHLU2DlNslh+sFac2HE\\nuYjxT1fbtTzxhan9nT11GYEEe+jiA3aGqUI6YGh8PmHNsabldZBFEnfXRKV6/59B\\nmdPCMAEwLmNcX6lec26er5C5AMRsUyMO5TlB349GT7VW1x3bXOQ4EPdeFKBGavo+\\nIaiugudtnfqPGe0mnUa9SGEVt/CMuwNOttKtnXi8XQKBgQD28O7d3u1+NXiWhVPl\\ndY6VB4u37fsoeYzIuXfKWvCeXkHiJZb+kD0evoMD3BQY7YgegQMLkZ2VnReomn0X\\nMe+OExMl7mmSbcCSQsz0nV3TrXqzbKL1itFR6WmIFb0FNiTmyYSV0TPBVPH0Iu2u\\nSXX9TJRAollip9PShkh8OKC2NQKBgQDTMAHGW3G3A1aI/At/oRTvuRDKwy7kE9Ap\\nnOQRm6sO5gEU/fHTeGwCzT/rtmCCEJLT0+vKjUQ7R3k2JFO4ejIJAVggWJKGeiXs\\nqLj3yWTGG/6QvYgm49P1I8NPMbceYZqeMbdVLXaM9eoX/BQYYVgTk/DjOJ5mhvSO\\nt43JAa2i6wKBgQCQ0HA0WwSJjBAArYWqvcr06KzuGzi73rRKDkIKwFP1e69FUOgA\\nt1OwkObCP7DlgX7e7Kd9hFKWp751GMwc6yLh/ZuwFeWBgq142IXbOeIJPeYEDMRw\\nHI1ubOR/csgbo0n2sdz4AuxIB2mw0z7xg5EXD26tjMfAjE0zW55sgsT0tQKBgCJi\\nGIfg73yqcHZRAtY+tZfp+4/ng0Ti7Os5ffBX9duxGs/+IFcbrgouvI912/SRGjae\\ntC8AHPHByzxDYdYRHwBUVbK+eOyoCnCjb6n4Ag43FJUgJoCeay3oKq5EG9o4beH/\\nmiemBT0h9ID/K45kmuBlH7z+xNtQcZSi0dS+DzUNAoGBAN7un9dVp8bHeJbSJzNA\\nae/I/z1dfNWWDtnIEl/zi6Gvla/qnHJ4zmN4R33i8aO1jPxti5MnBS+/HZr5jhHk\\nCOJ+OQKEvcvj6SHEdizC6ZcH3pbl7RnjdBB+cqoS8d6KjCJUarmwnNX+0Vd/F4qy\\nCtJS+GMP+cA+yuODvyfcbNEa\\n-----END PRIVATE KEY-----\\n\",\r\n"
				+ "  \"client_email\": \"firebase-adminsdk-30w6m@loja-angular-de706.iam.gserviceaccount.com\",\r\n"
				+ "  \"client_id\": \"112648474610597519142\",\r\n"
				+ "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n"
				+ "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n"
				+ "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n"
				+ "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-30w6m%40loja-angular-de706.iam.gserviceaccount.com\"\r\n"
				+ "}";
		try {
			in = org.apache.commons.io.IOUtils.toInputStream(source, "UTF-8");
			credential = GoogleCredentials.fromStream(in);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(res.getFile());
	 return credential;
	}
	
	@Bean
	public Storage storage() { 
		Credentials credential = null;
		Storage storage = null;
		InputStream in = null;
		String source = "{\r\n"
				+ "  \"type\": \"service_account\",\r\n"
				+ "  \"project_id\": \"loja-angular-de706\",\r\n"
				+ "  \"private_key_id\": \"0f6900bb1dec9ee46ffad4c70f88a13823da1f5d\",\r\n"
				+ "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDLtuPD79R+0tbS\\ny/XLn5bF6ellOD/H8I81+VkRVTynonGfVVqQYzaEuxMqha7o9sT5WB3tJf7QZUfL\\n8j/HE8IrauNbN77HCYKBPVCn+K6jGwZ9xHGn/qJemv9uYoA2aZJR3SOlO0oP+XgU\\nyN5H+3S0e1m0F518XNStihtfllt00gK+uuG94JTg1gCV4/eDFQIcbZ7/OgBT522g\\n7e0sCZ0ffKhvtYX50bCwDNSVoeNvrj8qY3zHov9veUjMEfdzlAaVA/K6fWJ1Fo1M\\nTaOO4Kaf8qftSsG1GLt5Hdg2r9D165H961IImOHOKYTHNYqvbq4M7f2CGvhC41O7\\nS8wFpcynAgMBAAECggEANurdeQ9Y/j2Riy9P2m5x43XdJRvmWdy4hsjrIXiUiu6I\\nafdFHGKaqmqSTqbOUeBgloWSP8G78Ws0UT9DwUPK1xCQLSGYjSi7pWKlsTqhvbfF\\n5rbEGxFSrWVkkdn9DW4lc2tFtkAHBcpvfE7wWYBo5FTSiHHLU2DlNslh+sFac2HE\\nuYjxT1fbtTzxhan9nT11GYEEe+jiA3aGqUI6YGh8PmHNsabldZBFEnfXRKV6/59B\\nmdPCMAEwLmNcX6lec26er5C5AMRsUyMO5TlB349GT7VW1x3bXOQ4EPdeFKBGavo+\\nIaiugudtnfqPGe0mnUa9SGEVt/CMuwNOttKtnXi8XQKBgQD28O7d3u1+NXiWhVPl\\ndY6VB4u37fsoeYzIuXfKWvCeXkHiJZb+kD0evoMD3BQY7YgegQMLkZ2VnReomn0X\\nMe+OExMl7mmSbcCSQsz0nV3TrXqzbKL1itFR6WmIFb0FNiTmyYSV0TPBVPH0Iu2u\\nSXX9TJRAollip9PShkh8OKC2NQKBgQDTMAHGW3G3A1aI/At/oRTvuRDKwy7kE9Ap\\nnOQRm6sO5gEU/fHTeGwCzT/rtmCCEJLT0+vKjUQ7R3k2JFO4ejIJAVggWJKGeiXs\\nqLj3yWTGG/6QvYgm49P1I8NPMbceYZqeMbdVLXaM9eoX/BQYYVgTk/DjOJ5mhvSO\\nt43JAa2i6wKBgQCQ0HA0WwSJjBAArYWqvcr06KzuGzi73rRKDkIKwFP1e69FUOgA\\nt1OwkObCP7DlgX7e7Kd9hFKWp751GMwc6yLh/ZuwFeWBgq142IXbOeIJPeYEDMRw\\nHI1ubOR/csgbo0n2sdz4AuxIB2mw0z7xg5EXD26tjMfAjE0zW55sgsT0tQKBgCJi\\nGIfg73yqcHZRAtY+tZfp+4/ng0Ti7Os5ffBX9duxGs/+IFcbrgouvI912/SRGjae\\ntC8AHPHByzxDYdYRHwBUVbK+eOyoCnCjb6n4Ag43FJUgJoCeay3oKq5EG9o4beH/\\nmiemBT0h9ID/K45kmuBlH7z+xNtQcZSi0dS+DzUNAoGBAN7un9dVp8bHeJbSJzNA\\nae/I/z1dfNWWDtnIEl/zi6Gvla/qnHJ4zmN4R33i8aO1jPxti5MnBS+/HZr5jhHk\\nCOJ+OQKEvcvj6SHEdizC6ZcH3pbl7RnjdBB+cqoS8d6KjCJUarmwnNX+0Vd/F4qy\\nCtJS+GMP+cA+yuODvyfcbNEa\\n-----END PRIVATE KEY-----\\n\",\r\n"
				+ "  \"client_email\": \"firebase-adminsdk-30w6m@loja-angular-de706.iam.gserviceaccount.com\",\r\n"
				+ "  \"client_id\": \"112648474610597519142\",\r\n"
				+ "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n"
				+ "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n"
				+ "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n"
				+ "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-30w6m%40loja-angular-de706.iam.gserviceaccount.com\"\r\n"
				+ "}";
		try {
			in = org.apache.commons.io.IOUtils.toInputStream(source, "UTF-8");
			credential = GoogleCredentials.fromStream(in);
			storage = StorageOptions.newBuilder().setCredentials(credential).setProjectId(projectId).build()
					.getService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(res.getFile());
	 return storage;
	}

	 
}
