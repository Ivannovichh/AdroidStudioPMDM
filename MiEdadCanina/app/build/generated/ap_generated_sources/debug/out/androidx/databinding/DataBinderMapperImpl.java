package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new es.medac.miedadcanina.DataBinderMapperImpl());
  }
}
