package org.demo.parcialmagneto.services;

import org.demo.parcialmagneto.dto.StatsResponse;
import org.demo.parcialmagneto.entities.Dna;
import org.demo.parcialmagneto.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class DnaService {

    private final DnaRepository dnaRepository;
    private final StatsService statsService;
    private static final int SEQUENCE_LENGTH = 4;

    // Constructor con inyección de dependencias
    @Autowired
    public DnaService(DnaRepository dnaRepository, StatsService statsService) {
        this.dnaRepository = dnaRepository;
        this.statsService = statsService;
    }

    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int sequenceCount = 0;

        // Verificamos filas
        sequenceCount += checkRows(dna, n);
        if (sequenceCount > 1) return true;
        sequenceCount += checkColumns(dna, n);
        if (sequenceCount > 1) return true;

        // Verificamos diagonales
        sequenceCount += checkDiagonals(dna, n);
        return sequenceCount > 1;
    }

    private static int checkRows(String[] dna, int n) {
        return (int) IntStream.range(0, n)
                .parallel()
                .map(i -> countSequences(dna[i]))
                .sum();
    }

    private static int countSequences(String row) {
        int count = 0;
        int currentCount = 1;
        char lastChar = row.charAt(0);

        for (int j = 1; j < row.length(); j++) {
            if (row.charAt(j) == lastChar) {
                currentCount++;
                if (currentCount == SEQUENCE_LENGTH) {
                    count++;
                }
            } else {
                lastChar = row.charAt(j);
                currentCount = 1;
            }
        }
        return count;
    }

    private static int checkColumns(String[] dna, int n) {
        return (int) IntStream.range(0, n)
                .parallel()
                .map(j -> countSequencesInColumn(dna, j, n))
                .sum();
    }

    private static int countSequencesInColumn(String[] dna, int col, int n) {
        int count = 0;
        int currentCount = 1;
        char lastChar = dna[0].charAt(col);

        for (int i = 1; i < n; i++) {
            if (dna[i].charAt(col) == lastChar) {
                currentCount++;
                if (currentCount == SEQUENCE_LENGTH) {
                    count++;
                }
            } else {
                lastChar = dna[i].charAt(col);
                currentCount = 1;
            }
        }
        return count;
    }

    private static int checkDiagonals(String[] dna, int n) {
        int countRightDiagonals = (int) IntStream.range(0, n - SEQUENCE_LENGTH + 1)
                .parallel()
                .map(i -> IntStream.range(0, n - SEQUENCE_LENGTH + 1)
                        .map(j -> countSequencesInDiagonal(dna, i, j, 1, 1))
                        .sum())
                .sum();

        int countLeftDiagonals = (int) IntStream.range(0, n - SEQUENCE_LENGTH + 1)
                .parallel()
                .map(i -> IntStream.range(SEQUENCE_LENGTH - 1, n)
                        .map(j -> countSequencesInDiagonal(dna, i, j, 1, -1))
                        .sum())
                .sum();

        return countRightDiagonals + countLeftDiagonals;
    }

    private static int countSequencesInDiagonal(String[] dna, int startX, int startY, int dx, int dy) {
        int count = 0;
        int currentCount = 1;
        char lastChar = dna[startX].charAt(startY);
        int n = dna.length;

        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            int nextX = startX + i * dx;
            int nextY = startY + i * dy;

            if (nextX < n && nextY < n && nextY >= 0) {
                if (dna[nextX].charAt(nextY) == lastChar) {
                    currentCount++;
                    if (currentCount == SEQUENCE_LENGTH) {
                        count++;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return count;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            return existingDna.get().isMutant();
        }

        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant;
    }

    // Llamada al metodo de instancia en statsService
    public StatsResponse getStats() {
        return statsService.getStats();
    }
}
