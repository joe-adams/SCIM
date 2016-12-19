package service

  import com.google.inject.ImplementedBy

@ImplementedBy(value=classOf[DataServiceImpl])
trait  DataService {

}

class DataServiceImpl extends DataService{
  //def apply():DataService=new DataService {}
}
