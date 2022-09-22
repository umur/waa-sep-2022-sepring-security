package henry.springsecurity.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    @Autowired
    private ModelMapper modelMapper;

    public <S, T> List<T> map(List<S> list, Class<T> targetClass) {
        return list.stream().map(item -> modelMapper.map(item, targetClass)).collect(Collectors.toList());
    }
}
