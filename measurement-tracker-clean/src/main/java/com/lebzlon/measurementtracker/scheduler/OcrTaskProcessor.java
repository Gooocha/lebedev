package com.lebzlon.measurementtracker.scheduler;

import com.lebzlon.measurementtracker.entity.*; import com.lebzlon.measurementtracker.repository.*; import com.lebzlon.measurementtracker.service.*; import jakarta.transaction.Transactional; import org.springframework.beans.factory.annotation.Value; import org.springframework.scheduling.annotation.Scheduled; import org.springframework.stereotype.Component; import java.nio.file.Path; import java.time.Instant;

@Component
public class OcrTaskProcessor {
    private final OcrTaskRepository tasks; private final OcrService ocr; private final MeterReadingRepository readings; private final FileStorageService storage; @Value("${app.ocr.tesseract-path:tesseract}") private String tesseract;
    public OcrTaskProcessor(OcrTaskRepository tasks,OcrService ocr,MeterReadingRepository readings,FileStorageService storage){this.tasks=tasks;this.ocr=ocr;this.readings=readings;this.storage=storage;}
    @Scheduled(fixedDelayString = "${app.ocr.scheduler-delay-ms:5000}") @Transactional
    public void process(){ tasks.findFirstByStatusOrderByCreatedAtAsc(OcrTaskStatus.NEW).ifPresent(this::handle);} private void handle(OcrTask t){ try{ t.setStatus(OcrTaskStatus.PROCESSING); Path proc=storage.preprocess(Path.of(t.getImagePath())); String raw=ocr.run(tesseract,proc); t.setOcrRawText(raw); var val=ocr.extractValue(raw); t.setExtractedValue(val); MeterReading r=new MeterReading(); r.setMeter(t.getMeter()); r.setTimestamp(t.getTimestamp()); r.setValue(val); r.setSource(ReadingSource.OCR); r.setImagePath(t.getImagePath()); r.setOcrRawText(raw); readings.save(r); t.setStatus(OcrTaskStatus.DONE);}catch(Exception e){ t.setStatus(OcrTaskStatus.FAILED); t.setErrorMessage(e.getMessage()); } t.setProcessedAt(Instant.now()); tasks.save(t);} }
