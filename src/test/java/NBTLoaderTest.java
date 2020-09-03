import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.kvintanaka.mcaprocessor.adapter.mca.MCAAdapter;
import com.kvintanaka.mcaprocessor.adapter.mca.MCAAdapterImplementation;
import com.kvintanaka.mcaprocessor.adapter.nbt.NBTAdapter;
import com.kvintanaka.mcaprocessor.converter.BookToJSONConverter;
import com.kvintanaka.mcaprocessor.converter.Converter;
import com.kvintanaka.mcaprocessor.filter.BookFilter;
import com.kvintanaka.mcaprocessor.filter.Filter;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;

public class NBTLoaderTest {

    @Test
    public void benchmark() throws URISyntaxException {
        long startTime, finishTime;

        System.out.println("+ Test: Benchmarking NBT reading speed");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) // Reading NBT File 1000 times to aquire the latency
        {
            try {
                // NOTE: For test file bigtest.nbt
                // bigtest.nbt is gathered from https://wiki.vg/NBT
                NamedTag tag = NBTUtil.read(new File(NBTLoaderTest.class.getResource("bigtest.nbt").toURI()), false);
                System.out.println(tag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        finishTime = System.currentTimeMillis();

        System.out.println("Benchmark completed in " + (finishTime - startTime) + " ms");
    }

    @Test
    public void ReadingMCATest() throws URISyntaxException, IOException {
        long startTime, finishTime;
        MCAFile region;

        System.out.println("+ Test: Benchmarking MCA reading speed");
        startTime = System.currentTimeMillis();
        region = MCAUtil.read(new File(NBTLoaderTest.class.getResource("r.0.0.mca").toURI()));
        for (int i = 0; i < 1024; i++) {
            System.out.println(region.getChunk(i));
        }
        finishTime = System.currentTimeMillis();

        System.out.println("Benchmark completed in " + (finishTime - startTime) + " ms");
    }

    @Test
    public void ReadingMCAToJSONTest() throws IOException, URISyntaxException {
        MCAAdapter testMCA = new MCAAdapterImplementation(
                new File(NBTLoaderTest.class.getResource("r.0.0.mca").toURI()).toPath());

        Filter filter = new BookFilter();
        Converter converter = new BookToJSONConverter();

        for (NBTAdapter chunk : testMCA.getChunks()) {
            List<JSONObject> filtered = filter.extract(chunk.toJSON());

            if (filtered.size() != 0) {
                converter.convert(filtered);
            }
        }
    }

    @Test
    public void iteratorTest() throws IOException, URISyntaxException {
        MCAAdapter testMCA = new MCAAdapterImplementation(
                new File(NBTLoaderTest.class.getResource("r.0.0.mca").toURI()).toPath());

        int index = 0;
        for (NBTAdapter chunk : testMCA.getChunks()) {
            System.out.println(chunk.toJSON());
        }
    }
}