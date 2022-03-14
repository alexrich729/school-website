package com.chegg.project;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

/**
 * 
 * Loads entity configuration and provides accessors for each entity type.
 * 
 * @author alexrich729
 *
 */
public class Config {
	
	/**
	 * This is the YAML object created directly from the configuration
	 */
	private Map<String, Object> yamlObj;
	
	/**
	 * Loads the entity configuration
	 * @throws IOException  if the config cannot be read
	 */
	public Config() throws IOException {
		Yaml yaml = new Yaml(new SafeConstructor() /* basic java objects only */);
		InputStream inputStream = this.getClass()
				  .getClassLoader()
				  .getResourceAsStream("entity-config.yml");
		if (inputStream == null)
			throw new IOException("Unable to read configuration file");
		yamlObj = yaml.load(inputStream);
		System.out.println(yamlObj);
	}

	/*
     * @param type is the type of entity we want to get fields for
     * @return all the fields configured for the given entity type
     */
    @SuppressWarnings("unchecked")
	 public List<Field> getFields(EntityType type) {
    	// the value we want to return
        ArrayList<Field> val = new ArrayList<>();
        List<Object> fields;
        
	    for (Map.Entry<String, Object> entry : yamlObj.entrySet()) {
	    	if (entry.getKey().equals(type.getName())) {
	    		fields = (List<Object>)((Map<String, Object>)entry.getValue()).get("fields");
	    		for (Object fieldObj : fields) {
	    			Map<String, Object> fieldMap = (Map<String, Object>)fieldObj;
	    			String fieldName = (String)fieldMap.get("name");
	    			String fieldType = (String)fieldMap.get("type");
	    			Boolean required = (Boolean)fieldMap.get("required");
	    			Boolean multi = (Boolean)fieldMap.get("multi");
	    			Field field = new Field(fieldName, null, FieldType.fromString(fieldType), required, multi);
	    			val.add(field);
	    		}
	    	}
	    	return val;
	    }
	    
	    throw new UnsupportedOperationException("No configuration for " + type.getName() +
	    		"; configuration only for entities" + yamlObj.keySet().toString());
		
	}
	
	/*
     * @param type is the type of entity we want to get fields for
     * @return all the fields configured as "required" for the given entity type
     */
	public List<Field> getRequiredFields(EntityType type) {
		List<Field> allFields = getFields(type);
		List<Field> val = new ArrayList<>();
		
		for (Field field : allFields) {
			if (field.isRequired())
				val.add(field);
		}
		
		return val;
		// return allFields.stream().filter(x->x.isRequired()).toList(); - not compatible with older javac
    }

    /**
     * @param type is the type of entity we want to get fields for
     * @return all the fields not configured as "required" for the given entity type
     */
    public List<Field> getOptionalFields(EntityType type) {
		List<Field> allFields = getFields(type);
		List<Field> val = new ArrayList<>();
		
		for (Field field : allFields) {
			if (! field.isRequired())
				val.add(field);
		}
		
		return val;
		// return allFields.stream().filter(x->!x.isRequired()).toList(); - not compatible with older javac
    }

}
