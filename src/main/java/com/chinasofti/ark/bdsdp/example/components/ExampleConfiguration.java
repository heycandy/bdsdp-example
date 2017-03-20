package com.chinasofti.ark.bdsdp.example.components;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import org.jvnet.hk2.annotations.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by White on 2017/3/20.
 */
@Service
public class ExampleConfiguration {

  private final String YML_SUFFIX = ".yml";
  private final String BASE_NAME = "base";
  private final String ENTRY_SUFFIX = ".entry";

  private final Yaml yaml;
  private final Map<String, Map> conf;

  private final String roleName;

  public ExampleConfiguration() throws FileNotFoundException {
    yaml = new Yaml();
    conf = Maps.newLinkedHashMap();

    Map baseConf = loadConf(BASE_NAME);

    conf.put(BASE_NAME, (Map) baseConf.get(BASE_NAME));

    roleName = getString(BASE_NAME + ENTRY_SUFFIX);

    Map roleConf = loadConf(roleName);

    conf.put(roleName, (Map) roleConf.get(roleName));

  }

  private Map loadConf(String roleName) throws FileNotFoundException {
    URL url = Resources.getResource(roleName + YML_SUFFIX);
    String pathname = url.getFile();
    File file = new File(pathname);
    Reader io = Files.newReader(file, Charsets.UTF_8);

    return yaml.loadAs(io, Map.class);
  }

  public Object get(String namespace) {
    if (Strings.isNullOrEmpty(namespace)) {
      throw new IllegalArgumentException("Illegal namespace '" + namespace + "'.");
    }

    String[] names = namespace.split("\\.");

    if (names.length < 1) {
      throw new IllegalArgumentException("Illegal namespace '" + namespace + "'.");
    }

    if (!conf.containsKey(names[0])) {
      throw new RuntimeException("Configuration does not contain '" + names[0] + "'.");
    }

    Map map = conf.get(names[0]);

    Object obj = null;
    for (int i = 1; i < names.length; i++) {
      obj = map.get(names[i]);
      if (i == names.length - 1) {
        break;
      } else if (obj == null) {
        String message = String.format("Need %s, but the '%s' is 'null'.", namespace, names[0]);
        throw new RuntimeException(message);
      } else if (!(obj instanceof Map)) {
        String message = String.format("Need %s, but the '%s' is '%s'.", namespace, names[0], obj);
        throw new RuntimeException(message);
      } else {
        map = (Map) obj;
      }
    }

    return obj;
  }

  public <T> T getAs(String namespace) {
    return (T) get(namespace);
  }

  public String getString(String namespace) {
    return getAs(namespace);
  }

  public Integer getInteger(String namespace) {
    return getAs(namespace);
  }

  public List getList(String namespace) {
    return getAs(namespace);
  }

  public Map getMap(String namespace) {
    return getAs(namespace);
  }

}

