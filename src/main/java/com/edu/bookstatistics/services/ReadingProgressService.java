package com.edu.bookstatistics.services;

import com.edu.bookstatistics.entities.ReadingProgress;
import com.edu.bookstatistics.repositories.ReadingProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingProgressService {

    private final ReadingProgressRepository progressRepository;

    /**
     * Добавляет новый прогресс чтения.
     *
     * @param progress Прогресс чтения.
     * @return Сохраненный прогресс чтения.
     */
    public ReadingProgress addProgress(ReadingProgress progress) {
        return progressRepository.save(progress);
    }

    /**
     * Получает прогресс чтения по ID книги.
     *
     * @param bookId ID книги.
     * @return Список прогресса чтения для указанной книги.
     */
    public List<ReadingProgress> getProgressByBookId(Long bookId) {
        return progressRepository.findByBookId(bookId);
    }
}