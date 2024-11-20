package ksmart.ks50team01.documentation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart.ks50team01.documentation.model.DocumentEntity;

@Mapper
public interface DocumentMapper {
	void insertDocumentHistory(DocumentEntity document);
	DocumentEntity getLatestDocument();
	List<DocumentEntity> getDocumentHistory();
	/**
	 * 해시값으로 문서 조회
	 */
	DocumentEntity findByFileHash(String fileHash);
}
