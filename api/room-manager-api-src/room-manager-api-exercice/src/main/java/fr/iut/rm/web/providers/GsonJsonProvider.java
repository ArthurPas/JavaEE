package fr.iut.rm.web.providers;


import com.google.inject.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Google JSON provider for Jersey REST API (Server SIDE)
 * It uses the Gson tool for serialisation. Null fields are omitted.
 */
@Provider
@Singleton
@Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_JSON, "text/json"})
@Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_JSON, "text/json"})
public final class GsonJsonProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object> {


    @Override
    public long getSize(final Object object, final Class<?> clazz, final Type type, final Annotation[] annotations, final MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(final Class<?> clazz, final Type type, final Annotation[] annotations, final MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(final Object object, final Class<?> clazz, final Type type, final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, Object> valueMap, final OutputStream out) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
        GsonFactory.getGson().toJson(object, clazz, writer);
        writer.close();
    }

    @Override
    public boolean isReadable(final Class<?> clazz, final Type type, final Annotation[] annotations, final MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(final Class<Object> clazz, final Type type, final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, String> valueMap, final InputStream in) throws IOException {

        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
        try {
            Object ret = GsonFactory.getGson().fromJson(reader, clazz);
            return ret;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        } finally {
            reader.close();
        }
    }
}