package com.myproject.service.impl;

import com.myproject.jpa.bean.Word;
import com.myproject.jpa.repository.WordRepository;
import com.myproject.service.IWord;
import com.myproject.vo.WordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordImpl implements IWord {

    @Autowired
    WordRepository repository;

    @Override
    public long addWord(WordVo wordVo) {
        Word word = Word.builder()
                .word(wordVo.getWord())
                .meaning(wordVo.getMeaning())
                .sentence(wordVo.getSentence())
                .build();
        repository.save(word);
        Word w = repository.findWordByWord(wordVo.getWord());
        return w.getId();
    }
}
