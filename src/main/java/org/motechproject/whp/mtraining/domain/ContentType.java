package org.motechproject.whp.mtraining.domain;

import org.apache.commons.lang.StringUtils;
import org.motechproject.mtraining.dto.ChapterDto;
import org.motechproject.mtraining.dto.CourseDto;
import org.motechproject.mtraining.dto.MessageDto;
import org.motechproject.mtraining.dto.ModuleDto;

import java.util.List;

public enum ContentType {
    COURSE {
        @Override
        public CourseDto toDto(String nodeName, String description, String fileName, List<Object> childDtos) {
            return new CourseDto(nodeName, description, (List<ModuleDto>) (Object) childDtos);
        }
    },
    MODULE {
        @Override
        public ModuleDto toDto(String nodeName, String description, String fileName, List<Object> childDtos) {
            return new ModuleDto(nodeName, description, (List<ChapterDto>) (Object) childDtos);
        }
    },
    CHAPTER {
        @Override
        public ChapterDto toDto(String nodeName, String description, String fileName, List<Object> childDtos) {
            return new ChapterDto(nodeName, description, (List<MessageDto>) (Object) childDtos);
        }
    },
    MESSAGE {
        @Override
        public MessageDto toDto(String nodeName, String description, String fileName, List<Object> childDtos) {
            return new MessageDto(nodeName, fileName, description);
        }
    };

    public static ContentType from(String nodeType) {
        return ContentType.valueOf(StringUtils.trimToEmpty(nodeType).toUpperCase());
    }

    public abstract Object toDto(String nodeName, String description, String fileName, List<Object> childDtos);
}
